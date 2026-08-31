package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientePessoaFisicaRepository extends JpaRepository<ClientePessoaFisica, UUID> {

    Optional<ClientePessoaFisica> findByIdAndAtivoTrue(UUID id);

    Optional<ClientePessoaFisica> findByCpfAndAtivoTrue(String cpf);

    boolean existsByCpf(String cpf);

    List<ClientePessoaFisica> findAllByAtivoTrue();

    List<ClientePessoaFisica> findAllByAtivoFalse();
}
