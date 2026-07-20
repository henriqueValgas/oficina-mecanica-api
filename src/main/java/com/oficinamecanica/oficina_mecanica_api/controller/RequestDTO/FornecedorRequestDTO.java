package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record FornecedorRequestDTO(

        @NotBlank
        @CNPJ
        String cnpj,

        @NotBlank
        String razaoSocial,

        @NotBlank
        String nomeFantasia

) {
}
