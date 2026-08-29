package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/funcionario")
@RequiredArgsConstructor
public class FuncionarioController implements ControllerUriSupport {

    private final FuncionarioService service;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> salvar(@RequestBody @Valid FuncionarioCreateRequestDTO request) {

        FuncionarioResponseDTO response = service.salvar(request);

        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid FuncionarioUpdateRequestDTO request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> inativarFuncionarioPorId(UUID id){

        service.inativarFuncionarioPorCpf(id);

        return ResponseEntity.noContent().build();
    }


}
