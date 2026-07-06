package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaFisicaRequestDTO(

        @NotBlank(message = "Nome é campo obrigatório")
        String nome,

        @CPF
        String cpf
) {
}
