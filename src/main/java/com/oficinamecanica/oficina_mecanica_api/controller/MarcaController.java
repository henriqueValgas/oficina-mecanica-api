package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.MarcaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/marcas")
@RequiredArgsConstructor
public class MarcaController implements ControllerUriSupport {
    private final MarcaService marcaService;


    @PostMapping
    public ResponseEntity<MarcaResponseDTO> salvar(@Valid @RequestBody MarcaCreateRequestDTO request){

        MarcaResponseDTO response = marcaService.salvar(request);

        URI uri = buildLocationUri(response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> atualizar(
            @PathVariable UUID id,@Valid @RequestBody MarcaUpdateResquestDTO request){

        return  ResponseEntity.ok().body(marcaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID id){

        marcaService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id){

        marcaService.reativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome")
    public ResponseEntity<MarcaResponseDTO> buscarPorNome(@RequestParam String nome){

        return ResponseEntity.ok().body(marcaService.buscarPorNome(nome));
    }
    @GetMapping("/nome/inativa")
    public ResponseEntity<MarcaResponseDTO> buscarPorNomeInativo(@RequestParam String nome){

        return ResponseEntity.ok().body(marcaService.buscarPorNomeInativo(nome));
    }

    @GetMapping
    public ResponseEntity<List<MarcaResponseDTO>> listarMarcas(){

        return ResponseEntity.ok(marcaService.listarAtivas());
    }

    @GetMapping("/inativas")
    public ResponseEntity<List<MarcaResponseDTO>> listarMarcasInativas(){

        return ResponseEntity.ok(marcaService.listarInativas());
    }

}
