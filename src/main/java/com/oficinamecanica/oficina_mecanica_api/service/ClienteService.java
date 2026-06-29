package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
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
    public ClienteResponseDTO create(ClienteRequestDto request) {
        Cliente cliente = ClienteMapper.toEntity(request);

        verificaClienteDuplicado(cliente.getCpf());

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


    public void deleteByCpf(String cpf) {

        Cliente cliente = buscaClientePorCpf(cpf);

        repository.delete(cliente);
    }

    public List<ClienteResponseDTO> listClientes(String nome, String cpf) {

        Specification<Cliente> spec = Specification
                .where(ClienteSpecification.nomeContem(nome)
                        .and(ClienteSpecification.cpfIgual(cpf)));

        return repository.findAll(spec).stream().map(ClienteMapper::toDTO).toList();
    }

    private Cliente buscaClientePorCpf(String cpf) {
        return repository.findByCpf(cpf).orElseThrow(() ->
                new RegistroNaoEncontradoException("Cliente não localizado ou não cadastrado"));
    }

    public void verificaClienteDuplicado(String cpf) {
         if (repository.existsByCpf(cpf)){
             throw new RegistroDuplicadoException("Cliente existente");
         }
    }
}

