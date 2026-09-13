package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
    boolean findByIdAndAtivoTrue(UUID id);

    List<Veiculo> findAllByAtivoTrue();

    List<Veiculo> findAllByAtivoFalse();
}
