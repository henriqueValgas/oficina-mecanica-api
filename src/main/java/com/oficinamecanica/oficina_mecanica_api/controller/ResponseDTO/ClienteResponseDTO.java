package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

public record ClienteResponseDTO(
        int id,
        String nome,
        String cpf,
        String telefone,
        String email) {
}
