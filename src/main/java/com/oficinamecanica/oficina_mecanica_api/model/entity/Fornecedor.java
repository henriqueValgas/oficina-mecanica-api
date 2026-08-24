package com.oficinamecanica.oficina_mecanica_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "fornecedor")
@Getter
@Setter
@NoArgsConstructor
public class Fornecedor extends PessoaJuridica {

}
