package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.builder.PessoaBuilder;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClientePessoaFisicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.ClientePessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientePessoaFisicaService {

    private final PessoaBuilder pessoaBuilder;
    private final ClientePessoaFisicaRepository repository;
    private final ClientePessoaFisicaMapper clientePessoaFisicaMapper;

    @Transactional
    public ClientePessoaFisicaResponseDTO salvarPessoaFisica(ClientePessoaFisicaCreateRequestDTO request) {

        ClientePessoaFisica clientePessoaFisica = clientePessoaFisicaMapper.toEntity(request);

        verificaCpfCadastrado(clientePessoaFisica.getCpf());

        List<Endereco> enderecos = pessoaBuilder.buildEnderecos(request.enderecos());
        enderecos.forEach(clientePessoaFisica::addEndereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());
        telefones.forEach(clientePessoaFisica::addTelefone);

        repository.save(clientePessoaFisica);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    @Transactional
    public ClientePessoaFisicaResponseDTO atualizaPessoaFisica(UUID id, ClientePessoaFisicaUpdateRequestDTO request) {

        ClientePessoaFisica clientePessoaFisica = buscaClienteId(id);

        clientePessoaFisicaMapper.toUpdate(request, clientePessoaFisica);

        if (request.endereco() != null) {
            pessoaBuilder.updateEndereco(request.endereco(), clientePessoaFisica.getEnderecos());
        }

        if (request.telefones() != null) {
            clientePessoaFisica.getTelefones().clear();

            for (TelefoneRequestDTO telefoneRequestDTO : request.telefones()) {
                Telefone telefone = new Telefone();

                telefone.setNumero(telefoneRequestDTO.numero());
                telefone.setTipo(telefoneRequestDTO.tipo());
                telefone.setPessoa(clientePessoaFisica);

                clientePessoaFisica.getTelefones().add(telefone);
            }
        }
        repository.save(clientePessoaFisica);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    @Transactional
    public void inativarPessoaFisica(UUID id) {

        ClientePessoaFisica clientePessoaFisica = buscaClienteId(id);

        clientePessoaFisica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaFisicaResponseDTO> listarPessoaFisicaAtiva() {

        List<ClientePessoaFisica> listaClientes = repository.findAllByAtivoTrue();
        return listaClientes.stream().map(clientePessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaFisicaResponseDTO> listarPessoaFisicaInativa() {

        List<ClientePessoaFisica> listaInativas = repository.findAllByAtivoFalse();
        return listaInativas.stream().map(clientePessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ClientePessoaFisicaResponseDTO buscarClientePessoaFisicaPorCpf(String cpf) {

        ClientePessoaFisica clientePessoaFisica = buscaClientePorCpfEAtivo(cpf);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    private void verificaCpfCadastrado(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("Cpf ja cadastrado");
        }
    }

    private ClientePessoaFisica buscaClientePorCpfEAtivo(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private ClientePessoaFisica buscaClienteId(UUID id) {

        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

}
