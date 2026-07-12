package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


public class Peca {

    private int id;

    private String nome;
    private String codigo;
    private String descricao;
    private String categoria;
    private BigDecimal valor;

    private Fabricante fabricante;

    private boolean ativo;

}
