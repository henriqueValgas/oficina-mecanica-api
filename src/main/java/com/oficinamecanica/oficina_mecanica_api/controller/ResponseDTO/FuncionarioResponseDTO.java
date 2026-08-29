package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record FuncionarioResponseDTO(

        UUID id,
        String nome,
        String cpf,
        String matricula,
        String cargo
) {
}
