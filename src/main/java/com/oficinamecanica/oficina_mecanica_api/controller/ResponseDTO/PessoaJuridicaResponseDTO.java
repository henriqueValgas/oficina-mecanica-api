package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import java.util.UUID;

public record PessoaJuridicaResponseDTO(

        UUID id,
        String cnpj,
        String razaosocial,
        String nomefantasia,
        String observacao

)
{
}
