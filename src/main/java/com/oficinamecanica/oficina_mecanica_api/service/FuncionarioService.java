package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.FuncionarioMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import com.oficinamecanica.oficina_mecanica_api.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final FuncionarioMapper mapper;

    @Transactional
    public FuncionarioResponseDTO salvar(@Valid FuncionarioCreateRequestDTO request) {

        Funcionario funcionario = mapper.toEntity(request);

        funcionario.getEnderecos().forEach(e -> e.setPessoa(funcionario));

        funcionario.getTelefones().forEach(t -> t.setPessoa(funcionario));

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
        if (!funcionario.isAtivo()) {
            throw new OperacaoInvalidaException("Funcionario esta inativo");
        }
        funcionario.setAtivo(false);
    }

    @Transactional
    public void ativaFuncionarioInativoPorId(UUID id) {
        Funcionario funcionario = buscaFuncionariPorId(id);
        if (funcionario.isAtivo()) {
            throw new OperacaoInvalidaException("Funcionario esta ativo");
        }
        funcionario.setAtivo(true);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorNumeroMatriculaAtivo(String matricula) {

        Funcionario funcionario = repository.findByMatriculaAndAtivoTrue(matricula)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrado"));

        return mapper.toDto(funcionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorNumeroMatriculaInativo(String matricula) {

        Funcionario funcionario = repository.findByMatriculaAndAtivoFalse(matricula)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrado"));

        return mapper.toDto(funcionario);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarFuncionariosAtivos() {
        List<Funcionario> funcionarios = repository.findAllByAtivoTrue();

        return funcionarios.stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarFuncionariosInativos() {
        List<Funcionario> listaFuncionariosInativos = repository.findAllByAtivoFalse();

        return listaFuncionariosInativos.stream().map(mapper::toDto).toList();

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

