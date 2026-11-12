package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.exceptions.DocumentoInvalidoException;
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
    private final ClientePessoaFisicaService clientePessoaFisicaService;
    private final ClientePessoaJuridicaService clientePessoaJuridicaService;


    public Pessoa buscarPorId(UUID id) {
        return pessoaRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscaPorDocumento(String documento) {

        String numeros = documento.replaceAll("\\D", "");

        if (numeros.length() == 11) {
            return clientePessoaFisicaService.buscarEntidadePorCpf(documento);
        }

        if (numeros.length() == 14) {
            return clientePessoaJuridicaService.buscarEntidaePorCnpj(documento);
        }

        throw new DocumentoInvalidoException("Documento inválido");
    }


}
