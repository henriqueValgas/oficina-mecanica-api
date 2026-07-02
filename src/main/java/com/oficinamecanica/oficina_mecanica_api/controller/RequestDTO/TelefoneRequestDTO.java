package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoTelefone;
import jakarta.validation.constraints.NotBlank;

public record TelefoneRequestDTO(

        @NotBlank
        String numero,
        TipoTelefone tipo
) {
}
