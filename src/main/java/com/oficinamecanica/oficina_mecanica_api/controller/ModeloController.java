package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloCreateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ModeloResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.ModeloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/modelos")
@RequiredArgsConstructor
public class ModeloController implements ControllerUriSupport {

    private final ModeloService modeloService;

    @PostMapping
    public ResponseEntity<ModeloResponseDTO> salvar(@Valid @RequestBody ModeloCreateResquestDTO request){

        ModeloResponseDTO response = modeloService.salvar(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ModeloResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody ModeloUpdateRequestDTO request){

        return ResponseEntity.ok().body(modeloService.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID id){

        modeloService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id){

        modeloService.reativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ModeloResponseDTO>> listar(){

        return ResponseEntity.ok().body(modeloService.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ModeloResponseDTO>> listarInativos(){

        return ResponseEntity.ok().body(modeloService.listarInativos());
    }


}
