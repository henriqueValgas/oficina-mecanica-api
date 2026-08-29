package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.FuncionarioMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import com.oficinamecanica.oficina_mecanica_api.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;

    @Transactional
    public FuncionarioResponseDTO salvar(@Valid FuncionarioCreateRequestDTO request) {

        Funcionario funcionario = mapper.toEntity(request);

        verificarFuncionarioCadastrado(funcionario);

        repository.save(funcionario);

        return mapper.toDto(funcionario);

    }

    @Transactional
    public FuncionarioResponseDTO update(UUID id, FuncionarioUpdateRequestDTO request) {

        Funcionario funcionario = buscaFuncionariPorId(id);

        mapper.toUpdate(request, funcionario);


        return mapper.toDto(funcionario);
    }

    @Transactional
    public void inativarFuncionarioPorCpf(UUID id) {

        Funcionario funcionario = buscaFuncionariPorId(id);

        funcionario.setAtivo(false);
    }

    private Funcionario buscaFuncionariPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Funcionario nâo cadastrado"));
    }

    private void verificarFuncionarioCadastrado(Funcionario funcionario) {
        if (repository.existsByCpf(funcionario.getCpf())) {
            throw new RegistroDuplicadoException("funcionario ja possui cadastro");
        }
    }
}
