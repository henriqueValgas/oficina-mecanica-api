package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController implements ControllerUriSupport{

    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> salvar(@Valid @RequestBody ServicoCreateRequestDTO request) {

        ServicoResponseDTO response = servicoService.salvar(request);

        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> atualizar(@PathVariable UUID id,@Valid @RequestBody ServicoUpdateResquestDTO request){

        return ResponseEntity.ok().body(servicoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable UUID id){
        servicoService.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable UUID id){
        servicoService.reativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ServicoResponseDTO>> listar(){

        return ResponseEntity.ok(servicoService.listar());
    }

    public ResponseEntity<List<ServicoResponseDTO>> listarInativos(){

        return ResponseEntity.ok(servicoService.listarInativos());
    }

}
