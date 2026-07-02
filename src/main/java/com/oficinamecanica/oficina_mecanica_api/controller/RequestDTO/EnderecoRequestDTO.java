package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.web.bind.annotation.PathVariable;

public record EnderecoRequestDTO(

        @Pattern(
                regexp = "^\\d{5}-?\\d{3}$",
                message = "CEP inválido"
        )
        String cep,

        String rua,

        String numero,

        String bairro,

        String cidade,

        String estado
)
{
}
