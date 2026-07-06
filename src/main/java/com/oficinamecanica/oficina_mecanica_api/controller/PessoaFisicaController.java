package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.service.PessoaFisicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoa_fisica")
@RequiredArgsConstructor
public class PessoaFisicaController {

    private final PessoaFisicaService service   ;

    @PostMapping
    public ResponseEntity<PessoaFisicaResponseDTO> inativarPessoaFisica(@RequestBody String cpf) {

        return ResponseEntity.ok(service.inativarClientePessoaFisica(cpf));
    }
}
