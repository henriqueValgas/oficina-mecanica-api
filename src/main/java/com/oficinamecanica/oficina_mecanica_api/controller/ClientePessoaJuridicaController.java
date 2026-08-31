package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.BuscaPessoaJuridicaPorCnpjRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.ClientePessoaJuridicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoa_juridica")
@RequiredArgsConstructor
public class ClientePessoaJuridicaController implements ControllerUriSupport {

    private final ClientePessoaJuridicaService service;

    @PostMapping
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> salvarPessoaJurica(
            @Valid @RequestBody ClientePessoaJuridicaCreateRequestDTO request) {

        ClientePessoaJuridicaResponseDTO response = service.salvarPessoaJuridica(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> atualizaPessoaJurica(
            @PathVariable UUID id,
            @Valid @RequestBody ClientePessoaJuridicaUpdateRequestDTO request){

        return ResponseEntity.ok(service.atualizaPessoaJuridica(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> inativaPessoaJurica(@PathVariable UUID id) {

        service.inativaPessoaJurica(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buscar-por-cnpj")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> buscaPessoaJuridica(
            @RequestBody BuscaPessoaJuridicaPorCnpjRequestDTO request) {

        return ResponseEntity.ok(service.buscarPessoaJuridicaPorCnpj(request.cnpj()));
    }

    @GetMapping("/listar-ativos")
    public ResponseEntity<List<ClientePessoaJuridicaResponseDTO>> listaPessoaJuridicaAtivos() {

        return ResponseEntity.ok(service.listarPessoaJuridicaAtivos());
    }

    @GetMapping("/listar-inativos")
    public ResponseEntity<List<ClientePessoaJuridicaResponseDTO>> listaPessoaJuridicaInativos() {

        return ResponseEntity.ok(service.listarPessoaJuridicaInativos());
    }
}
