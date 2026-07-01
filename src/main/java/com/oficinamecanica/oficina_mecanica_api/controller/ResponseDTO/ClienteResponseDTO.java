package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.List;

public record ClienteResponseDTO(
        int id,
        String nome,
        String cpf,
        String email,
        List<TelefoneResponseDTO> telefones,
        EnderecoResponseDTO endereco

){
}
