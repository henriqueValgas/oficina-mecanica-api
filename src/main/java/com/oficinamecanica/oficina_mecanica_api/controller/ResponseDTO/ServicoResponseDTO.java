package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoResponseDTO(

        UUID id,
        String codigo,
        String nome,
        String descricao,
        Integer tempoPrevistoMinutos,
        BigDecimal valorPadrao
) {
}
