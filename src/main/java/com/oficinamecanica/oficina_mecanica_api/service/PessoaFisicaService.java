package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.PessoaFisicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

    private final ClienteService clienteService;
    private final PessoaFisicaRepository repository;
    private final PessoaFisicaMapper pessoaFisicaMapper;

    @Transactional
    public PessoaFisicaResponseDTO salvarPessoaFisica(PessoaFisicaRequestDTO request) {


        PessoaFisica pessoaFisica = pessoaFisicaMapper.toEntity(request);

        if (clienteExiste(pessoaFisica.getCpf())) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }

        Endereco endereco = clienteService.buildEndereco(request.endereco());
        pessoaFisica.setEndereco(endereco);

        List<Telefone> telefones = new ArrayList<Telefone>();

        telefones = clienteService.buildTelefones(request.telefones());
        telefones.forEach(pessoaFisica::addTelefone);

        repository.save(pessoaFisica);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    @Transactional
    public PessoaFisicaResponseDTO inativarClientePessoaFisica(Long id) {

        PessoaFisica pessoaFisica = buscaClienteId(id);

        pessoaFisica.setAtivo(false);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    @Transactional(readOnly = true)
    public PessoaFisicaResponseDTO buscarClientePessoaFisicaPorCpf(String cpf) {

        PessoaFisica pessoaFisica = buscaClientePorCpfEAtivo(cpf);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    private boolean clienteExiste(String cpf) {
        return repository.existsByCpf(cpf);
    }

    private PessoaFisica buscaClientePorCpfEAtivo(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private PessoaFisica buscaClienteId(Long id) {

        return repository.findByIdAndAtivoTrue(id).orElseThrow(()-> new RegistroNaoEncontradoException("Cliente não localizado"));
    }

}
