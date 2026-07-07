package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

public record PessoaJuridicaRequestDTO(

        @NotBlank(message = "Razão social é campo obrigatório")
        String razaosocial,

        String nomefantasia,

        @CNPJ
        String cnpj,

        String observacao,

        @Email
        String email,

        @Valid
        List<TelefoneRequestDTO> telefones,

        @Valid
        EnderecoRequestDTO endereco
)
{
}
