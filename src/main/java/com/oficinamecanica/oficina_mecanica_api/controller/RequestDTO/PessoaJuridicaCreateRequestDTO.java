package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

public record PessoaJuridicaCreateRequestDTO(

        @CNPJ
        String cnpj,

        @NotBlank(message = "Razão social é campo obrigatório")
        String razaosocial,

        @NotBlank
        String nomefantasia,

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
