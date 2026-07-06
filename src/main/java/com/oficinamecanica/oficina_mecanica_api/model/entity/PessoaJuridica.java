package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "pessoa_juridica")
@Getter
@Setter
@NoArgsConstructor
public class PessoaJuridica extends Cliente {

    @Id
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razaosocial")
    private String razaoSocial;

    @Column(name = "nomefantasia")
    private String nomeFantasia;

    @Column(name = "observacao")
    private String observacao;

}
