package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClienteMapper;
import com.oficinamecanica.oficina_mecanica_api.model.Cliente;
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

    private final ClienteRepository repository;

    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDto request) {
        Cliente cliente = ClienteMapper.toEntity(request);

        Cliente saved = repository.save(cliente);

        return ClienteMapper.toDTO(saved);
    }

    public ClienteResponseDTO update(String cpf, ClienteRequestDto request) {

        Cliente cliente = buscaClientePorCpf(cpf);

        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());

        Cliente update = repository.save(cliente);

        return ClienteMapper.toDTO(update);
    }


    public void deletePorCpf(String cpf) {

        repository.deleteByCpf(cpf);
    }

    public List<ClienteResponseDTO> listarClientes(String nome, String cpf) {
        List<Cliente> clientes;

        Specification<Cliente> spec = Specification
                .where(ClienteSpecification.nomeContem(nome))
                .and(ClienteSpecification.cpfIgual(cpf));

        clientes = repository.findAll(spec);

        return clientes.stream().map(ClienteMapper::toDTO).toList();
    }

    private Cliente buscaClientePorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new RegistroNaoEncontradoException("Cliente não localizado ou não cadastrado"));
    }
}

