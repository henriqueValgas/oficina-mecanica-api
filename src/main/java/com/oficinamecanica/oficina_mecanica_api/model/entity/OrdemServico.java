package com.oficinamecanica.oficina_mecanica_api.model.entity;

import com.oficinamecanica.oficina_mecanica_api.model.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ordem_servico")
@Getter
@Setter
@NoArgsConstructor
public class OrdemServico extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero_os")
    private String numeroOs;

    @Column(name = "quilometragem_entrada")
    private int quilometragemEntrada;

    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @Column(name = "data_aprovacao")
    private LocalDate dataAprovacao;

    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "defeito_relatado")
    private String defeitoRelatado;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "sub_total_peca")
    private BigDecimal subTotalPecas;

    @Column(name = "sub_total_servico")
    private BigDecimal subTotalServicos;

    @Column(name = "descontos")
    private BigDecimal descontos;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "status")
    private String status;

    @Column(name = "ativo")
    private boolean ativo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_peca_id")
    private ItemPeca  itemPeca;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_pervico_id")
    private ItemServico  itemServico;

}
