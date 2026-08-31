package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.builder.PessoaBuilder;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClientePessoaJuridicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaJuridica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.ClientePessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientePessoaJuridicaService {

    private final PessoaBuilder pessoaBuilder;
    private final ClientePessoaJuridicaRepository repository;
    private final ClientePessoaJuridicaMapper mapperPessoaJuridica;

    @Transactional
    public ClientePessoaJuridicaResponseDTO salvar(ClientePessoaJuridicaCreateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = mapperPessoaJuridica.toEntity(request);

        verificarCnpjCadastrado(clientePessoaJuridica.getCnpj());

        List<Endereco> enderecos = pessoaBuilder.buildEnderecos(request.enderecos());

        enderecos.forEach(clientePessoaJuridica::addEndereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());

        telefones.forEach(clientePessoaJuridica::addTelefone);

        repository.save(clientePessoaJuridica);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO atualizar(UUID id, ClientePessoaJuridicaUpdateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPorId(id);

        mapperPessoaJuridica.toUpdate(request, clientePessoaJuridica);

        if (request.endereco() != null) {
            pessoaBuilder.updateEndereco(request.endereco(), clientePessoaJuridica.getEnderecos());
        }

        if (request.telefones() != null) {
            clientePessoaJuridica.getTelefones().clear();

            for (TelefoneRequestDTO telefoneRequestDTO : request.telefones()) {
                Telefone telefone = new Telefone();

                telefone.setNumero(telefoneRequestDTO.numero());
                telefone.setTipo(telefoneRequestDTO.tipo());

                telefone.setPessoa(clientePessoaJuridica);

                clientePessoaJuridica.getTelefones().add(telefone);
            }
        }
        repository.save(clientePessoaJuridica);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    @Transactional
    public void inativar(UUID id) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPorId(id);

        clientePessoaJuridica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarAtivos() {

        List<ClientePessoaJuridica> clientesAtivos = repository.findAllByAtivoTrue();

        return clientesAtivos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarInativos() {

        List<ClientePessoaJuridica> clientesInativos = repository.findAllByAtivoFalse();

        return clientesInativos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO buscarPorCnpj(@CNPJ String cnpj) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPorCnpjAtivo(cnpj);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    private void verificarCnpjCadastrado(String cnpj){
        if (repository.existsByCnpj(cnpj)) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }
    }

    private ClientePessoaJuridica buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private ClientePessoaJuridica buscarPorCnpjAtivo(String cnpj) {
         return repository.findByCnpjAndAtivoTrue(cnpj)
                 .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
