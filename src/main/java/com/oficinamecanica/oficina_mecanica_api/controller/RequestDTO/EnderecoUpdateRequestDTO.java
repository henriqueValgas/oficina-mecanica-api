package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.TipoEndereco;

import java.util.UUID;

public record EnderecoUpdateRequestDTO(

    UUID id,
    TipoEndereco tipoEndereco,
    String cep,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado

) {
}
