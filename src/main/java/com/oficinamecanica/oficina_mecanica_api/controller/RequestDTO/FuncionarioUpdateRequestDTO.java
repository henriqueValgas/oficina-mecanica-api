package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Cargo;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioUpdateRequestDTO(
        String nome,
        String cpf,
        String email,
        String matricula,
        LocalDate dataContratacao,
        Cargo cargo,
        List<TelefoneRequestDTO> telefones,
        EnderecoUpdateRequestDTO enderecos
) {
}
