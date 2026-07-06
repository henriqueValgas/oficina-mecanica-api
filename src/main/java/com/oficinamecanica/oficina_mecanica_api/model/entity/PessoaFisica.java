package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
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
public class PessoaFisica extends Cliente {

    @Id
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

}
