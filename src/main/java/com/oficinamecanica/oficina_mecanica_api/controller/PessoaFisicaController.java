package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.BuscaClientePorCpfRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.PessoaFisicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pessoa_fisica")
@RequiredArgsConstructor
public class PessoaFisicaController implements GenericController{

    private final PessoaFisicaService service;

    @PostMapping
    public ResponseEntity<PessoaFisicaResponseDTO> salvarPessoaFisica(@RequestBody PessoaFisicaRequestDTO request) {

        PessoaFisicaResponseDTO response = service.salvarPessoaFisica(request);
        URI uri = gerarHeaderLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponseDTO> inativarPessoaFisica(@PathVariable Long id) {

        return ResponseEntity.ok(service.inativarClientePessoaFisica(id));
    }

    @PostMapping("/buscar-por-cpf")
    public ResponseEntity<PessoaFisicaResponseDTO> buscarPessoaFisica(@RequestBody BuscaClientePorCpfRequestDTO request) {

        return ResponseEntity.ok(service.buscarClientePessoaFisicaPorCpf(request.cpf()));
    }
}
