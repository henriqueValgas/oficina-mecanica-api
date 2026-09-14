package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.math.BigDecimal;

public record ServicoCreateRequestDTO(

        String nome,
        String descricao,
        Integer tempoPrevistoMinutos,
        BigDecimal valorPadrao
) {
}
