package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClientePessoaFisicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.repository.ClientePessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientePessoaFisicaService {

    private final ClientePessoaFisicaRepository repository;
    private final ClientePessoaFisicaMapper clientePessoaFisicaMapper;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    @Transactional
    public ClientePessoaFisicaResponseDTO salvar(ClientePessoaFisicaCreateRequestDTO request) {

        ClientePessoaFisica clientePessoaFisica = clientePessoaFisicaMapper.toEntity(request);

        verificarCpfCadastrado(clientePessoaFisica.getCpf());

        enderecoService.preencherEnderecoComViaCep(clientePessoaFisica);

        clientePessoaFisica.getEnderecos().forEach(e -> e.setPessoa(clientePessoaFisica));

        clientePessoaFisica.getTelefones().forEach(t -> t.setPessoa(clientePessoaFisica));

        repository.save(clientePessoaFisica);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    @Transactional
    public ClientePessoaFisicaResponseDTO atualizar(UUID id, ClientePessoaFisicaUpdateRequestDTO request) {

        ClientePessoaFisica clientePessoaFisica = buscarPorId(id);

        clientePessoaFisicaMapper.toUpdate(request, clientePessoaFisica);

        enderecoService.atualizarEndereco(clientePessoaFisica, request.enderecos());

        telefoneService.atualizarTelefones(clientePessoaFisica, request.telefones());

        repository.save(clientePessoaFisica);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    @Transactional
    public void inativar(UUID id) {

        ClientePessoaFisica clientePessoaFisica = buscarPorId(id);

        clientePessoaFisica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaFisicaResponseDTO> listarAtivos() {

        List<ClientePessoaFisica> listaClientes = repository.findAllByAtivoTrue();
        return listaClientes.stream().map(clientePessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaFisicaResponseDTO> listarInativos() {

        List<ClientePessoaFisica> listaInativas = repository.findAllByAtivoFalse();
        return listaInativas.stream().map(clientePessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ClientePessoaFisicaResponseDTO buscarPorCpf(String cpf) {

        ClientePessoaFisica clientePessoaFisica = buscarPorCpfAtivo(cpf);

        return clientePessoaFisicaMapper.toDTO(clientePessoaFisica);
    }

    private void verificarCpfCadastrado(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("Cpf ja cadastrado");
        }
    }

    private ClientePessoaFisica buscarPorCpfAtivo(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private ClientePessoaFisica buscarPorId(UUID id) {

        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
