package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.List;

public record PessoaFisicaUpdateRequestDTO(

        String nome,

        String email,

        List<TelefoneRequestDTO> telefones,

        EnderecoRequestDTO endereco
) {
}
