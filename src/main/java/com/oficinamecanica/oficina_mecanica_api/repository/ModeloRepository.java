package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModeloRepository extends JpaRepository<Modelo, UUID> {


    Optional<Modelo> findByIdAndAtivoTrue(UUID uuid);

    List<Modelo> findAllByAtivoTrue();

    List<Modelo> findAllByAtivoFalse();
}
