package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoTelefone;

import java.util.UUID;

public record TelefoneUpdateRequestDTO(
        UUID id,
        String numero,
        TipoTelefone tipo
) {
}
