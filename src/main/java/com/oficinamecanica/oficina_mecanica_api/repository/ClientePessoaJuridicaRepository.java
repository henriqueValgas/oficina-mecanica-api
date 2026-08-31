package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientePessoaJuridicaRepository extends JpaRepository<ClientePessoaJuridica, UUID> {

    boolean existsByCnpj(String cnpj);

    List<ClientePessoaJuridica> findAllByAtivoTrue();

    List<ClientePessoaJuridica> findAllByAtivoFalse();

    Optional<ClientePessoaJuridica> findByCnpjAndAtivoTrue(String cnpj);
}
