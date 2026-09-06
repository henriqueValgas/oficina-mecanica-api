package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;


import jakarta.validation.constraints.NotBlank;

public record MarcaCreateRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String paisOrigem

) {
}
