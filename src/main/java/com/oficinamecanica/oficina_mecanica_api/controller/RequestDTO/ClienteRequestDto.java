package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record ClienteRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Cpf é obrigatório")
        @CPF
        String cpf,

        @Email
        String email,

        @Valid
        List<TelefoneRequestDTO> telefones,

        @Valid
        EnderecoRequestDTO endereco
){
}
