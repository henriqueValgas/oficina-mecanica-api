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
@RequestMapping("/clientesPJ")
@RequiredArgsConstructor
public class ClientePessoaJuridicaController implements ControllerUriSupport {

    private final ClientePessoaJuridicaService service;

    @PostMapping
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> salvar(
            @Valid @RequestBody ClientePessoaJuridicaCreateRequestDTO request) {

        ClientePessoaJuridicaResponseDTO response = service.salvar(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody ClientePessoaJuridicaUpdateRequestDTO request){

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> inativar(@PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cnpj")
    public ResponseEntity<ClientePessoaJuridicaResponseDTO> buscaPorCnpf(
            @RequestBody BuscaPessoaJuridicaPorCnpjRequestDTO request) {

        return ResponseEntity.ok(service.buscarPorCnpj(request.cnpj()));
    }

    @GetMapping
    public ResponseEntity<List<ClientePessoaJuridicaResponseDTO>> listarClientes() {

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ClientePessoaJuridicaResponseDTO>> listarClientesInativos() {

        return ResponseEntity.ok(service.listarInativos());
    }
}
