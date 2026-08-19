package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record PessoaFisicaResponseDTO(

        UUID id,
        String cpf,
        String nome
){
}
