package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
public class Funcionario extends PessoaFisica {

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private String matricula;

    private LocalDate dataAdmissao;
}

