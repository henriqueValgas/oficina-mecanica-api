package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.BuscaPessoaFisicaPorCpfRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.ClientePessoaFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientesPF")
@RequiredArgsConstructor
public class ClientePessoaFisicaController implements ControllerUriSupport {

    private final ClientePessoaFisicaService service;

    @PostMapping
    public ResponseEntity<ClientePessoaFisicaResponseDTO> salvar(@Valid @RequestBody ClientePessoaFisicaCreateRequestDTO request) {

        ClientePessoaFisicaResponseDTO response = service.salvar(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody ClientePessoaFisicaUpdateRequestDTO request)
    {

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> inativar(@PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cpf")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> buscarPorCpf(@Valid @RequestBody BuscaPessoaFisicaPorCpfRequestDTO request) {

        return ResponseEntity.ok(service.buscarPorCpf(request.cpf()));
    }

    @GetMapping
    public ResponseEntity<List<ClientePessoaFisicaResponseDTO>> listarClientes() {

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ClientePessoaFisicaResponseDTO>> listarClientesInativos() {

        return ResponseEntity.ok(service.listarInativos());
    }
}
