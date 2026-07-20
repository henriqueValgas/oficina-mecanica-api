package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByIdAndAtivoTrue(Long id);

    List<Fornecedor> findAllByAtivoTrue();

    List<Fornecedor> findAllByAtivoFalse();
}
