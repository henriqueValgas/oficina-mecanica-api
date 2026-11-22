package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPecaResponseDTO(
        UUID id,
        String nomePeca,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal desconto,
        BigDecimal descontoPercentual,
        BigDecimal valorTotal
) {
}
