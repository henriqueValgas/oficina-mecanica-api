package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Cargo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioCreateRequestDTO(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        String email,

        @NotBlank
        String matricula,

        @NotNull
        LocalDate dataAdmissao,

        Cargo cargo,

        @Valid
        List<TelefoneRequestDTO> telefones,

        @Valid
        List<EnderecoRequestDTO> enderecos

) {
}

