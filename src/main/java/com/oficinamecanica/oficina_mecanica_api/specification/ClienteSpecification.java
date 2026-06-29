package com.oficinamecanica.oficina_mecanica_api.specification;

import com.oficinamecanica.oficina_mecanica_api.model.Cliente;
import org.springframework.data.jpa.domain.Specification;

public class ClienteSpecification {

    public static Specification<Cliente> nomeContem(String nome){

        return (root, query, cb) -> nome == null ? null :
                cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");

    }

    public static Specification<Cliente> cpfIgual(String cpf){
        return ((root, query, cb) -> cpf == null ? null :
                cb.equal(root.get("cpf"),cpf.trim()));
    }
}
