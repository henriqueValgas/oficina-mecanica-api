package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record ModeloResponseDTO(
        UUID id,
        String nome,
        boolean ativo
) {
}
