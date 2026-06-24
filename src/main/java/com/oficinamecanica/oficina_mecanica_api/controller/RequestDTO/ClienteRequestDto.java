package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Cpf é obrigatório")
        @CPF
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String telefone,

        String email
)
{
}
