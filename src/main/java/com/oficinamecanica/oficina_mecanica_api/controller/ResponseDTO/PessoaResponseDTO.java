package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.List;

public record PessoaResponseDTO(
        int id,
        String email,
        List<TelefoneResponseDTO> telefones,
        EnderecoResponseDTO endereco

){
}
