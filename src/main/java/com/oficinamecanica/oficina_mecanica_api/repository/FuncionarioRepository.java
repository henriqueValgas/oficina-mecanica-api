package com.oficinamecanica.oficina_mecanica_api.repository;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {

    boolean existsByCpf(String cpf);

    Optional<Funcionario> findByMatriculaAndAtivoTrue(String matricula);

    Optional<Funcionario> findByMatriculaAndAtivoFalse(String matricula);

    List<Funcionario> findAllByAtivoTrue();

    List<Funcionario> findAllByAtivoFalse();
}
