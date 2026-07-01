package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequestDTO(

        @NotBlank
        String cep,

        @NotBlank
        String rua,

        @NotBlank
        String numero,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        String estado
)
{
}
