package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime time,
        Integer status,
        String erro,
        Object mensagem,
        String patch
) {
}
