package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record MarcaUpdateResquestDTO(

        UUID id,

        @NotBlank
        String nome,

        @NotBlank
        String paisOrigem
) {
}
