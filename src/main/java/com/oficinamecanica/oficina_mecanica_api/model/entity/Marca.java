package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "marca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    @Id
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "pais_origem", nullable = false)
    private String paisOrigem;

    @Column(nullable = false)
    private boolean ativo;

}
