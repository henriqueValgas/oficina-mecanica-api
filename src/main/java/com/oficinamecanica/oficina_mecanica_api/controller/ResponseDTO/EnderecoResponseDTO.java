package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoEndereco;

public record EnderecoResponseDTO(

        TipoEndereco tipoEndereco,
        String cep,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado
) {
}
