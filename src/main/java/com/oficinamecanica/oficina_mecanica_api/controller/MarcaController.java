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
import java.util.UUID;

@RestController
@RequestMapping("/marca")
@RequiredArgsConstructor
public class MarcaController {
    private final MarcaService marcaService;
    private final ControllerUriSupport controllerUriSupport;

    @PostMapping
    public ResponseEntity<MarcaResponseDTO> salvar(@Valid @RequestBody MarcaCreateRequestDTO request){

        MarcaResponseDTO response = marcaService.salvar(request);

        URI uri = controllerUriSupport.buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MarcaResponseDTO> atualizar(
            @PathVariable UUID id,@Valid @RequestBody MarcaUpdateResquestDTO request){

        return  ResponseEntity.ok().body(marcaService.atualiza(id, request));
    }

    public ResponseEntity<MarcaResponseDTO> buscarPorNome(@RequestParam String nome){

        return ResponseEntity.ok().body(marcaService.buscarPorNome(nome));
    }

}
