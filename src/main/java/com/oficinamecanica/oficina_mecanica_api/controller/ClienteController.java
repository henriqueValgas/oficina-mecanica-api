package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController implements GenericController {

    private final ClienteService clienteService;

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> cadastrarCliente(@Valid @RequestBody ClienteRequestDto request) {

        ClienteResponseDTO response = clienteService.salvar(request);

        URI uri = gerarHeaderLocationUri(response.id());

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("{cpf}")
    public ResponseEntity<ClienteResponseDTO> alterarCliente(@PathVariable String cpf, @Valid @RequestBody ClienteRequestDto request) {

        ClienteResponseDTO response = clienteService.update(cpf, request);

        URI uri = gerarHeaderLocationUri(response.id());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Void> delete(@RequestParam String cpf) {

        clienteService.deletePorCpf(cpf);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ClienteResponseDTO>> listarClientes(
            @Valid @RequestParam String nome, String cpf){

        return ResponseEntity.ok(clienteService.listarClientes(nome,cpf));
    }

}
