package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.util.List;

public record ClienteRequestDto(

        @Email
        String email,

        TelefoneRequestDTO telefone,

        @Valid
        List<TelefoneRequestDTO> telefones,

        @Valid
        EnderecoRequestDTO endereco
)
{
}
