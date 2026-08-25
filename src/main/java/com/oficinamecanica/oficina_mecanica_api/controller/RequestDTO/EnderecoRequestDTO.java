package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoEndereco;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequestDTO(

        TipoEndereco tipoEndereco,

        @Pattern(
                regexp = "^\\d{5}-?\\d{3}$",
                message = "CEP inválido"
        )
        String cep,

        String rua,

        String numero,

        String complemento,

        String bairro,

        String cidade,

        String estado
){
}
