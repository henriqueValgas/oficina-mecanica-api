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

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<FuncionarioResponseDTO> inativarFuncionarioPorId(
            @PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<FuncionarioResponseDTO> ativaFuncionarioInativoPorId(@PathVariable UUID id) {

        service.reativar(id);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/funcionario-matricula-ativo")
    public ResponseEntity<FuncionarioResponseDTO> buscaPorNumeroMatriculaAtivo(
            @RequestParam String matricula) {

        return ResponseEntity.ok(service.buscarPorMatricula(matricula));
    }

    @GetMapping("funcionario-matricula-inativo")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorNumeroMatriculaInativo(
            @RequestParam String matricula) {

        return ResponseEntity.ok(service.buscarPorMatriculaInativa(matricula));
    }

    @GetMapping("listar-funcionarios-ativos")
    public ResponseEntity<List<FuncionarioResponseDTO>> listarFuncionariosAtivos() {

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("listar-funcionarios-inativos")
    public ResponseEntity<List<FuncionarioResponseDTO>> listarFuncionariosInativos() {

        return ResponseEntity.ok(service.listarInativos());
    }
}


