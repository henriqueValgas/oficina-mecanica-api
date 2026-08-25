package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import java.util.List;
import java.util.UUID;

public record PessoaJuridicaUpdateRequestDTO(

        UUID id,

        String razaoSocial,

        String nomeFantasia,

        String inscricaoEstadual,

        String observacoes,

        String email,

        List<TelefoneRequestDTO> telefones,

        EnderecoUpdateRequestDTO endereco
){
}
