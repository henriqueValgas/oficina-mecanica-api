package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

    Optional<PessoaFisica> findByIdAndAtivoTrue(Long id);

    Optional<PessoaFisica> findByCpfAndAtivoTrue(String cpf);

    boolean existsByCpf(String cpf);

    List<PessoaFisica> findAllByAtivoTrue();

    List<PessoaFisica> findAllByAtivoFalse();
}
