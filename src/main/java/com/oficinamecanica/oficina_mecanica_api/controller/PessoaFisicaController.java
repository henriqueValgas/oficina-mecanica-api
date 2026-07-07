package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.BuscaClientePorCpfRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.service.PessoaFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa_fisica")
@RequiredArgsConstructor
public class PessoaFisicaController implements ControllerUriSupport {

    private final PessoaFisicaService service;

    @PostMapping
    public ResponseEntity<PessoaFisicaResponseDTO> salvarPessoaFisica(@Valid @RequestBody PessoaFisicaCreateRequestDTO request) {

        PessoaFisicaResponseDTO response = service.salvarPessoaFisica(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponseDTO> alteraDadosPessoaFisica(
            @PathVariable Long id,
            @Valid @RequestBody PessoaFisicaUpdateRequestDTO request)
    {

        return ResponseEntity.ok(service.atualizaPessoaFisica(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaFisicaResponseDTO> inativarPessoaFisica(@PathVariable Long id) {

        return ResponseEntity.ok(service.inativarClientePessoaFisica(id));
    }

    @PostMapping("/buscar-por-cpf")
    public ResponseEntity<PessoaFisicaResponseDTO> listarPessoaFisica(@RequestBody BuscaClientePorCpfRequestDTO request) {

        return ResponseEntity.ok(service.buscarClientePessoaFisicaPorCpf(request.cpf()));
    }

    @GetMapping("/listar-ativos")
    public ResponseEntity<List<PessoaFisicaResponseDTO>> listarPessoaFisicaAtiva() {

        return ResponseEntity.ok(service.listarPessoaFisicaAtiva());
    }

    @GetMapping("/listar-inativos")
    public ResponseEntity<List<PessoaFisicaResponseDTO>> listarPessoaFisicaInativos() {

        return ResponseEntity.ok(service.listarPessoaFisicaInativa());
    }
}
