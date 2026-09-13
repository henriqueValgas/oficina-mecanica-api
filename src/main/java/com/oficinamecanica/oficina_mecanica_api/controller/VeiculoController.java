package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.VeiculoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Veiculo;
import com.oficinamecanica.oficina_mecanica_api.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController implements ControllerUriSupport{

    private final VeiculoService veiculoService;

    @PostMapping
    public ResponseEntity<VeiculoResponseDTO> salvar(@Valid @RequestBody VeiculoCreateRequestDTO request) {

        VeiculoResponseDTO response = veiculoService.salvar(request);

        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody VeiculoUpdateRequestDTO request) {

        return ResponseEntity.ok(veiculoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}/iativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID id) {

        veiculoService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id) {
        veiculoService.reativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VeiculoResponseDTO>> listar() {

        veiculoService.listar();

        return ResponseEntity.ok().build();
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<VeiculoResponseDTO>> listarInativos() {

        veiculoService.listarInativos();

        return ResponseEntity.ok().build();
    }

}
