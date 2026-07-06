package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Integer> {

    Optional<PessoaFisica> existsById(Long id);

    Optional<PessoaFisica> findByCpfAndAtivoTrue(String cpf);

    Optional<PessoaFisica> findByCpf(String cpf);
}
