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
@RequestMapping("/pessoa_fisica")
@RequiredArgsConstructor
public class ClientePessoaFisicaController implements ControllerUriSupport {

    private final ClientePessoaFisicaService service;

    @PostMapping
    public ResponseEntity<ClientePessoaFisicaResponseDTO> salvarPessoaFisica(@Valid @RequestBody ClientePessoaFisicaCreateRequestDTO request) {

        ClientePessoaFisicaResponseDTO response = service.salvar(request);
        URI uri = buildLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> atualizaPessoaFisica(
            @PathVariable UUID id,
            @Valid @RequestBody ClientePessoaFisicaUpdateRequestDTO request)
    {

        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> inativarPessoaFisica(@PathVariable UUID id) {

        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/buscar-por-cpf")
    public ResponseEntity<ClientePessoaFisicaResponseDTO> listarPessoaFisica(@Valid @RequestBody BuscaPessoaFisicaPorCpfRequestDTO request) {

        return ResponseEntity.ok(service.buscarPorCpf(request.cpf()));
    }

    @GetMapping("/listar-ativos")
    public ResponseEntity<List<ClientePessoaFisicaResponseDTO>> listarPessoaFisicaAtiva() {

        return ResponseEntity.ok(service.listarAtivos());
    }

    @GetMapping("/listar-inativos")
    public ResponseEntity<List<ClientePessoaFisicaResponseDTO>> listarPessoaFisicaInativos() {

        return ResponseEntity.ok(service.listarInativos());
    }
}
