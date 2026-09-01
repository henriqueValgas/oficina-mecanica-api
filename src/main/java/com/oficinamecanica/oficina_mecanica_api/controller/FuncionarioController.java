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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
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
    public ResponseEntity<FuncionarioResponseDTO> atualizar(
            @PathVariable UUID id,
            @RequestBody @Valid FuncionarioUpdateRequestDTO request) {

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(
            @PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {

        service.reativar(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/matricula")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorNumeroMatricula(
            @RequestParam String matricula) {

        return ResponseEntity.ok(service.buscarPorMatricula(matricula));
    }

    @GetMapping("/matricula/inativo")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorNumeroMatriculaInativo(
            @RequestParam String matricula) {

        return ResponseEntity.ok(service.buscarPorMatriculaInativa(matricula));
    }

    @GetMapping()
    public ResponseEntity<List<FuncionarioResponseDTO>> listarFuncionarios() {

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<FuncionarioResponseDTO>> listarFuncionariosInativos() {

        return ResponseEntity.ok(service.listarInativos());
    }
}


