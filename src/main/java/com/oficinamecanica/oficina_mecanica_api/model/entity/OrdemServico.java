package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
