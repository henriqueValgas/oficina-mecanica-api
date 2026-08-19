package com.oficinamecanica.oficina_mecanica_api.model.entity;

import com.oficinamecanica.oficina_mecanica_api.model.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "fabricante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fabricante extends Auditable {

    @Id
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "pais_origem", nullable = false)
    private String paisOrigem;

    @Column(nullable = false)
    private boolean ativo;

}
