package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FabricanteRepository extends JpaRepository<Fabricante, UUID> {
}
