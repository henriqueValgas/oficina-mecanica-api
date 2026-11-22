package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPecaRequestDTO(
        UUID pecaId,
        Integer quantidade,
        BigDecimal desconto,
        String observacoes
) {
}
