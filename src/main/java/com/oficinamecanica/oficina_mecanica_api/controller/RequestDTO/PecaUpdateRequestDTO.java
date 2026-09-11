package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.math.BigDecimal;
import java.util.UUID;

public record PecaUpdateRequestDTO(

        UUID id,

        String nome,

        String codigo,

        String descricao,

        BigDecimal custo,

        BigDecimal valorVenda
){
}
