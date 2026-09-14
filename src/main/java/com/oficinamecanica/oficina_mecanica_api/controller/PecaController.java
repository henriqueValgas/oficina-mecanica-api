package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaCreateRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PecaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.PecaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pecas")
@RequiredArgsConstructor
public class PecaController implements ControllerUriSupport {

    private final PecaService pecaService;

    @PostMapping
    public ResponseEntity<PecaResponseDTO> salvar(@Valid @RequestBody PecaCreateRequestDto request){

        PecaResponseDTO response = pecaService.salvar(request);

        URI uri = buildLocationUri(response.id());

        return  ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PecaResponseDTO> atualizar(UUID id, @Valid @RequestBody PecaUpdateRequestDTO request){

        return ResponseEntity.ok().body(pecaService.atualiza(id,request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID id){

        pecaService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id){

        pecaService.reativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PecaResponseDTO>> listar(){

        return ResponseEntity.ok(pecaService.listar());
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<PecaResponseDTO>> listarInativas(){

        return ResponseEntity.ok(pecaService.listarInativas());
    }

}
