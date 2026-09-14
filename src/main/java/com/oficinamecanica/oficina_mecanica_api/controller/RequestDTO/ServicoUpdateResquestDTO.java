package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record ServicoUpdateResquestDTO(

        UUID id,
        String nome,
        String descricao,
        Integer tempoPrevistoMinutos,
        BigDecimal valorPadrao
) {
}
