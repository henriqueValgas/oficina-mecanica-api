package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa_juridica")
@RequiredArgsConstructor
public class PessoaJuridicaController implements ControllerUriSupport {

    private final PessoaJuridicaService service;

    @PostMapping
    public ResponseEntity<PessoaJuridicaResponseDTO> salvarPessoaJurica(
            @Valid @RequestBody PessoaJuridicaCreateRequestDTO request) {

        PessoaJuridicaResponseDTO response = service.salvarPessoaJuridica(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PessoaJuridicaResponseDTO> atualizaPessoaJurica(
            @PathVariable Long id,
            @Valid @RequestBody PessoaJuridicaUpdateRequestDTO request)
    {

        return ResponseEntity.ok(service.atualizaPessoaJuridica(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaJuridicaResponseDTO> inativaPessoaJurica(@PathVariable Long id) {

        service.inativaPessoaJurica(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buscar-por-cnpj")
    public ResponseEntity<PessoaJuridicaResponseDTO> buscaPessoaJuridica(
            @RequestBody PessoaJuridicaCreateRequestDTO request) {

        return ResponseEntity.ok(service.buscarPessoaJuridicaPorCnpj(request.cnpj()));
    }

    @GetMapping("/listar-ativos")
    public ResponseEntity<List<PessoaJuridicaResponseDTO>> listaPessoaJuridicaAtivos() {

        return ResponseEntity.ok(service.listarPessoaJuridicaAtivos());
    }

    @GetMapping("/listar-inativos")
    public ResponseEntity<List<PessoaJuridicaResponseDTO>> listaPessoaJuridicaInativos() {

        return ResponseEntity.ok(service.listarPessoaJuridicaInativos());
    }
}
