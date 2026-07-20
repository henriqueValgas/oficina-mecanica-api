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

@RestController
@RequestMapping("/fornecedor")
@RequiredArgsConstructor
public class FornecedorController implements ControllerUriSupport {

    private final FornecedorService service;

    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> salvarFornecedor(
            @Valid @RequestBody FornecedorRequestDTO request) {

        FornecedorResponseDTO response = service.salvarFornecedor(request);

        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizaFornecedor(
            @PathVariable Long id,
            @RequestBody FornecedorRequestDTO request) {

        FornecedorResponseDTO response = service.atualizaFornecedor(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> inativarFornecedor(@PathVariable Long id) {

        service.inativarFornecedor(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar-fornecedor-ativos")
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedorAtivos(){

        List<FornecedorResponseDTO> listaFornecedorAtivos = service.listarFornecedorAtivos();

        return ResponseEntity.ok(listaFornecedorAtivos);
    }

    @GetMapping("lista-fornecedor-inativos")
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedorInativos(){

        List<FornecedorResponseDTO> listaFornecedorInativos = service.listarFornecedorInativo();

        return ResponseEntity.ok(listaFornecedorInativos);

    }

}
