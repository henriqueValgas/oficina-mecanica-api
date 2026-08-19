package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, UUID> {

    boolean existsByCnpj(String cnpj);

    List<PessoaJuridica> findAllByAtivoTrue();

    List<PessoaJuridica> findAllByAtivoFalse();

    Optional<PessoaJuridica> findByCnpjAndAtivoTrue( String cnpj);
}
