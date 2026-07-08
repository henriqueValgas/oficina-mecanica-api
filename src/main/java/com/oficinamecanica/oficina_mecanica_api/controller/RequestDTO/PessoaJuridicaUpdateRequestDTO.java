package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.List;

public record PessoaJuridicaUpdateRequestDTO(

        String razaosocial,

        String nomefantasia,

        String observacao,

        String email,

        List<TelefoneRequestDTO> telefones,

        EnderecoRequestDTO endereco
){
}
