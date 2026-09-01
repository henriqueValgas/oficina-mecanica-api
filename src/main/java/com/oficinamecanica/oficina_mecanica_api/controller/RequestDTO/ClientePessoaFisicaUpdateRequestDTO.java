package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.List;

public record ClientePessoaFisicaUpdateRequestDTO(

        String nome,

        String email,

        List<TelefoneUpdateRequestDTO> telefones,

        EnderecoUpdateRequestDTO enderecos
) {
}
