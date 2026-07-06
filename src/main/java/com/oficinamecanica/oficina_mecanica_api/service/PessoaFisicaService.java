package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.PessoaFisicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

    private final PessoaFisicaRepository repository;
    private final PessoaFisicaMapper pessoaFisicaMapper;

    @Transactional
    public PessoaFisicaResponseDTO inativarClientePessoaFisica(String cpf) {

        PessoaFisica pessoaFisica = buscaClientePorCpf(cpf);

        pessoaFisica.setAtivo(false);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    private PessoaFisica buscaClientePorCpf(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
