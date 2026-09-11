package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record PecaCreateRequestDto (
        @NotBlank
        String nome,

        @NotBlank
        String codigo,

        @NotBlank
        String descricao,

        @NotBlank
        BigDecimal custo,

        @NotBlank
        BigDecimal valorVenda
){
}
