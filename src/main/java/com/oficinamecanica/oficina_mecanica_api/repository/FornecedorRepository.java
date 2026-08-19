package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    Optional<Fornecedor> findByIdAndAtivoTrue(UUID id);

    List<Fornecedor> findAllByAtivoTrue();

    List<Fornecedor> findAllByAtivoFalse();
}
