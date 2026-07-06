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

    //modificar o delete de cliente nao usar o cpf para excluir e usar o softdelete
    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable String cpf) {

        clienteService.deleteByCpf(cpf);

        return ResponseEntity.noContent().build();
    }

}
