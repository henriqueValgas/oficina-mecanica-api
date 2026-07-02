package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoTelefone;

public record TelefoneResponseDTO(

        String numero,
        TipoTelefone tipo
) {
}
