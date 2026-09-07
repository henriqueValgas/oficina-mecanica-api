package com.oficinamecanica.oficina_mecanica_api.model.entity;

import com.oficinamecanica.oficina_mecanica_api.model.base.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "peca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Peca extends Auditable {

    @Id
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "custo", nullable = false)
    private BigDecimal custo;

    @Column(name = "valor_venda", nullable = false, scale = 2)
    private BigDecimal valorVenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @Column(nullable = false)
    private boolean ativo;

}
