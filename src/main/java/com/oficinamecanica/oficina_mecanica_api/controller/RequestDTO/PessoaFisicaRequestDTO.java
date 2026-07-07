package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record PessoaFisicaRequestDTO(

        @NotBlank(message = "Nome é campo obrigatório")
        String nome,

        @CPF
        String cpf,

        @Email
        String email,


        @Valid
        List<TelefoneRequestDTO> telefones,

        @Valid
        EnderecoRequestDTO endereco
)
{
}
