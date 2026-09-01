package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FornecedorRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FornecedorResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.FornecedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fornecedores")
@RequiredArgsConstructor
public class FornecedorController implements ControllerUriSupport {

    private final FornecedorService service;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> salvar(
            @Valid @RequestBody FornecedorRequestDTO request) {

        FornecedorResponseDTO response = service.salvar(request);

        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody FornecedorRequestDTO request) {

        FornecedorResponseDTO response = service.atualizar(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedores(){

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedoresInativos(){

        return ResponseEntity.ok(service.listarInativos());
    }
}
