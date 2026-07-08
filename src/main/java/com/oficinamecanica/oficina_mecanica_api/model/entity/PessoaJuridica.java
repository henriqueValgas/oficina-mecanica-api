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

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "razao_social")
    private String razaosocial;

    @Column(name = "nome_fantasia")
    private String nomefantasia;

    @Column(name = "observacao")
    private String observacao;

}
