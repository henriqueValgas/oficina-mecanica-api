package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Integer> {

    void existsById(Long id);

    Optional<PessoaJuridica> findById(Long id);

    boolean existsByCnpj(String cnpj);

    List<PessoaJuridica> findAllByAtivoTrue();

    List<PessoaJuridica> findAllByAtivoFalse();

    Optional<PessoaJuridica> findByCnpjAndAtivoTrue( String cnpj);
}
