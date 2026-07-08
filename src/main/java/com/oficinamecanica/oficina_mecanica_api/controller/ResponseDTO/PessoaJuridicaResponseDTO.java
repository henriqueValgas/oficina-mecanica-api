package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

public record PessoaJuridicaResponseDTO(

        Long id,
        String cnpj,
        String razaosocial,
        String nomefantasia,
        String observacao

)
{
}
