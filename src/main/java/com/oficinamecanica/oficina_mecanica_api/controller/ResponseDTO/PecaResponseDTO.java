package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record PecaResponseDTO(

        UUID id,
        String nome,
        String codigo,
        String descricao,
        BigDecimal custo,
        BigDecimal valorVenda
) {
}
