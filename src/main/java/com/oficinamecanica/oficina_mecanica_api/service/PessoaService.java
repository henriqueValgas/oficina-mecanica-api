package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Pessoa;
import com.oficinamecanica.oficina_mecanica_api.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa buscarPorId(UUID id) {
        return pessoaRepository
                .findById(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }
}
