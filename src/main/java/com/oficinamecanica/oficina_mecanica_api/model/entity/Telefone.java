package com.oficinamecanica.oficina_mecanica_api.model.entity;

import com.oficinamecanica.oficina_mecanica_api.model.base.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "telefone")
@Getter
@Setter
@NoArgsConstructor
public class Telefone extends Auditable {

    @Id
    private UUID id;

    private String numero;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

}
