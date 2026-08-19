package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record FornecedorResponseDTO(
        UUID id,
        String cnpj,
        String razaoSocial,
        String nomeFantasia
) {
}
