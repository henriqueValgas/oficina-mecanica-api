package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
public class Endereco{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco", nullable = false)
    private TipoEndereco tipoEndereco;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    @Column(name = "uf", columnDefinition = "CHAR(2)")
    private String estado;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;
}
