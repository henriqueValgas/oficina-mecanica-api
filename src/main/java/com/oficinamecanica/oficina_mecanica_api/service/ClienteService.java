package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepService;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClienteMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Cliente;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.ClienteRepository;
import com.oficinamecanica.oficina_mecanica_api.specification.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteMapper clienteMapper;
    private final ClienteRepository repository;
    private final ViaCepService viaCepService;

    @Transactional
    public ClienteResponseDTO create(ClienteRequestDto request) {

        Cliente cliente = clienteMapper.toEntity(request);

        verificaClienteDuplicado(cliente.getCpf());

        Endereco endereco = buildEndereco(request.endereco());
        cliente.setEndereco(endereco);

        for (Telefone telefone : buildTelefones(request.telefones())) {
            cliente.addTelefone(telefone);
        }

        Cliente saved = repository.save(cliente);

        return clienteMapper.toDTO(saved);
    }

    @Transactional
    public ClienteResponseDTO update(String cpf, ClienteRequestDto request) {

        Cliente cliente = buscaClientePorCpf(cpf);

        clienteMapper.updateEntityFromDTO(request, cliente);

        return clienteMapper.toDTO(cliente);
    }

    @Transactional
    public void deleteByCpf(String cpf) {

        Cliente cliente = buscaClientePorCpf(cpf);

        cliente.setAtivo(false);
    }

    public List<ClienteResponseDTO> listClientes(String nome, String cpf) {

        Specification<Cliente> spec = Specification
                .where(ClienteSpecification.ativo()
                        .and(ClienteSpecification.nomeContem(nome))
                        .and(ClienteSpecification.cpfIgual(cpf)));

        return repository.findAll(spec).stream().map(clienteMapper::toDTO).toList();
    }

    private Cliente buscaClientePorCpf(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf).orElseThrow(() ->
                new RegistroNaoEncontradoException("Cliente não localizado ou não cadastrado"));
    }

    private void verificaClienteDuplicado(String cpf) {

        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("Cliente existente");
        }
    }

    private Telefone buildTelefone(TelefoneRequestDTO request) {

        Telefone telefone = new Telefone();

        telefone.setNumero(request.numero());
        telefone.setTipo(request.tipo());

        return telefone;
    }

    private List<Telefone> buildTelefones(List<TelefoneRequestDTO> request) {

        List<Telefone> telefones = new ArrayList<>();

        for (TelefoneRequestDTO requestDTO : request) {
            telefones.add(buildTelefone(requestDTO));
        }
        return telefones;
    }

    private Endereco buildEndereco(EnderecoRequestDTO request) {

        String cep = request.cep();
        Endereco endereco = new Endereco();

        if (cep != null && !cep.isBlank()) {

            ViaCepResponseDTO viaCep = viaCepService.getViaCep(cep);
            endereco.setRua(viaCep.logradouro());
            endereco.setNumero(request.numero());
            endereco.setCep(viaCep.cep());
            endereco.setBairro(viaCep.bairro());
            endereco.setCidade(viaCep.localidade());
            endereco.setEstado(viaCep.uf());

        } else {

            endereco.setRua(request.rua());
            endereco.setNumero(request.numero());
            endereco.setCep(request.cep());
            endereco.setBairro(request.bairro());
            endereco.setCidade(request.cidade());
            endereco.setEstado(request.estado());

        }

        return endereco;
    }

}

