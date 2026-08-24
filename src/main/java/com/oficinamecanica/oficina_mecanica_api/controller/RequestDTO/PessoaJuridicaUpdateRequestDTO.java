package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.List;
import java.util.UUID;

public record PessoaJuridicaUpdateRequestDTO(

        UUID id,

        String razaosocial,

        String nomefantasia,

        String observacoes,

        String email,

        List<TelefoneRequestDTO> telefones,

        EnderecoUpdateRequestDTO endereco
){
}
