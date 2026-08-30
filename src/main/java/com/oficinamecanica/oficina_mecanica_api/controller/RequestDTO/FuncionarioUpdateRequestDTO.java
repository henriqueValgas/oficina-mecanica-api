package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Cargo;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioUpdateRequestDTO(
        String nome,
        String cpf,
        String email,
        String matricula,
        LocalDate dataAdmissao,
        Cargo cargo,
        List<TelefoneRequestDTO> telefones,
        EnderecoUpdateRequestDTO enderecos
) {
}

