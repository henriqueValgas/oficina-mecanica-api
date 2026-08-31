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
    public ClientePessoaJuridicaResponseDTO salvarPessoaJuridica(ClientePessoaJuridicaCreateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = mapperPessoaJuridica.toEntity(request);

        verificaCnpjCadastrado(clientePessoaJuridica.getCnpj());

        List<Endereco> enderecos = pessoaBuilder.buildEnderecos(request.enderecos());

        enderecos.forEach(clientePessoaJuridica::addEndereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());

        telefones.forEach(clientePessoaJuridica::addTelefone);

        repository.save(clientePessoaJuridica);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO atualizaPessoaJuridica(UUID id, ClientePessoaJuridicaUpdateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = buscaClienteId(id);

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
    public void inativaPessoaJurica(UUID id) {

        ClientePessoaJuridica clientePessoaJuridica = buscaClienteId(id);

        clientePessoaJuridica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarPessoaJuridicaAtivos() {

        List<ClientePessoaJuridica> listaClientesPessoaJuridicaAtivos = repository.findAllByAtivoTrue();

        return listaClientesPessoaJuridicaAtivos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarPessoaJuridicaInativos() {

        List<ClientePessoaJuridica> listaClientesPessoaJuridicaInativos = repository.findAllByAtivoFalse();

        return listaClientesPessoaJuridicaInativos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO buscarPessoaJuridicaPorCnpj(@CNPJ String cnpj) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPessoaJuridicaCnpjEAtivo(cnpj);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    private void verificaCnpjCadastrado(String cnpj){
        if (repository.existsByCnpj(cnpj)) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }
    }

    private ClientePessoaJuridica buscaClienteId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    public ClientePessoaJuridica buscarPessoaJuridicaCnpjEAtivo(String cnpj) {
         return repository.findByCnpjAndAtivoTrue(cnpj)
                 .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
