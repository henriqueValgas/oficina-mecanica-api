package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

public record FornecedorResponseDTO(
        Long id,
        String cnpj,
        String razaoSocial,
        String nomeFantasia
) {
}
