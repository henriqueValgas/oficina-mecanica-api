package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record ClientePessoaFisicaResponseDTO(

        UUID id,
        String cpf,
        String nome
){
}
