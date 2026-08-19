package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, UUID> {

    Optional<PessoaFisica> findByIdAndAtivoTrue(UUID id);

    Optional<PessoaFisica> findByCpfAndAtivoTrue(String cpf);

    boolean existsByCpf(String cpf);

    List<PessoaFisica> findAllByAtivoTrue();

    List<PessoaFisica> findAllByAtivoFalse();
}
