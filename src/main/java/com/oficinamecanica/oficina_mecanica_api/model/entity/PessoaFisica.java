package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "pessoa_fisica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica extends Pessoa {

    @Id
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

}
