package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

public record EnderecoResponseDTO(
        String cep,
        String rua,
        String numero,
        String bairro,
        String cidade,
        String estado
) {
}
