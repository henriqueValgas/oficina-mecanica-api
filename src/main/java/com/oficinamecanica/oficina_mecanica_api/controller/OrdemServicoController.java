package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.OrdemServicoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.OrdemServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.OrdemServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/ordemservico")
@RequiredArgsConstructor
public class OrdemServicoController implements ControllerUriSupport{

    private final OrdemServicoService ordemServicoService;

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> salvar(@Valid @RequestBody OrdemServicoRequestDTO request) {

        OrdemServicoResponseDTO response = ordemServicoService.salvar(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

}
