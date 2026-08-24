package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.UUID;

public record EnderecoUpdateRequestDTO(

    UUID id,
    String cep,
    String rua,
    String numero,
    String complemento,
    String bairro,
    String cidade,
    String estado

) {
}
