package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClienteMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Cliente;
import com.oficinamecanica.oficina_mecanica_api.repository.ClienteRepository;
import com.oficinamecanica.oficina_mecanica_api.specification.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository repository;

    @Transactional
    public ClienteResponseDTO create(ClienteRequestDto request) {

        Cliente cliente = clienteMapper.toEntity(request);

        verificaClienteDuplicado(cliente.getCpf());

        Cliente saved = repository.save(cliente);

        return clienteMapper.toDTO(saved);
    }

    public ClienteResponseDTO update(String cpf, ClienteRequestDto request) {

        Cliente cliente = buscaClientePorCpf(cpf);

        clienteMapper.updateEntityFromDTO(request, cliente);

        return clienteMapper.toDTO(cliente);
    }

    public void deleteByCpf(String cpf) {

        Cliente cliente = buscaClientePorCpf(cpf);

        repository.delete(cliente);
    }

    public List<ClienteResponseDTO> listClientes(String nome, String cpf) {

        Specification<Cliente> spec = Specification
                .where(ClienteSpecification.nomeContem(nome)
                        .and(ClienteSpecification.cpfIgual(cpf)));

        return repository.findAll(spec).stream().map(clienteMapper::toDTO).toList();
    }

    private Cliente buscaClientePorCpf(String cpf) {

        return repository.findByCpf(cpf).orElseThrow(() ->
                new RegistroNaoEncontradoException("Cliente não localizado ou não cadastrado"));
    }

    private void verificaClienteDuplicado(String cpf) {

        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("Cliente existente");
        }
    }
}

