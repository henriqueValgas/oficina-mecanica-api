package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record PessoaJuridicaRequestDTO(

        @NotBlank(message = "Razão social é campo obrigatório")
        String razaosocial,

        String nomefantasia,

        @CNPJ
        String cnpj,

        String observacao
){
}
