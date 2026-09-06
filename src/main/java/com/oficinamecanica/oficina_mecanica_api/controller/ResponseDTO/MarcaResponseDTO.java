package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record MarcaResponseDTO(

        UUID id,
        String nome,
        String paisOrigem,
        boolean ativo
) {
}
