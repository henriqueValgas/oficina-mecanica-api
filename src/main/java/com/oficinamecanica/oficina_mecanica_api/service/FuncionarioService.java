package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepService;
import com.oficinamecanica.oficina_mecanica_api.mapper.EnderecoMapper;
import com.oficinamecanica.oficina_mecanica_api.mapper.FuncionarioMapper;
import com.oficinamecanica.oficina_mecanica_api.mapper.TelefoneMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
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
    private final FuncionarioMapper funcionarioMapper;
    private final ViaCepService viaCepService;
    private final EnderecoMapper enderecoMapper;
    private final TelefoneMapper telefoneMapper;

    @Transactional
    public FuncionarioResponseDTO salvar(@Valid FuncionarioCreateRequestDTO request) {

        verificarCpfCadastrado(request.cpf());

        Funcionario funcionario = funcionarioMapper.toEntity(request);

        preencherEnderecoComViaCep(funcionario);

        funcionario.getEnderecos().forEach(e -> e.setPessoa(funcionario));

        funcionario.getTelefones().forEach(t -> t.setPessoa(funcionario));

        repository.save(funcionario);

        return funcionarioMapper.toDto(funcionario);
    }

    @Transactional
    public FuncionarioResponseDTO atualizar(UUID id, FuncionarioUpdateRequestDTO request) {

        Funcionario funcionario = buscarPorId(id);

        funcionarioMapper.toUpdate(request, funcionario);

        atualizarEndereco(funcionario, request.enderecos());

        atualizarTelefones(funcionario, request.telefones());

        return funcionarioMapper.toDto(funcionario);
    }

    @Transactional
    public void inativar(UUID id) {
        Funcionario funcionario = buscarPorId(id);
        if (!funcionario.isAtivo()) {
            throw new OperacaoInvalidaException("Funcionario esta inativo");
        }
        funcionario.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id) {
        Funcionario funcionario = buscarPorId(id);
        if (funcionario.isAtivo()) {
            throw new OperacaoInvalidaException("Funcionario esta ativo");
        }
        funcionario.setAtivo(true);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorMatricula(String matricula) {

        Funcionario funcionario = repository.findByMatriculaAndAtivoTrue(matricula)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrado"));

        return funcionarioMapper.toDto(funcionario);
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorMatriculaInativa(String matricula) {

        Funcionario funcionario = repository.findByMatriculaAndAtivoFalse(matricula)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Funcionario não encontrado"));

        return funcionarioMapper.toDto(funcionario);
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarAtivos() {
        List<Funcionario> funcionariosAtivos = repository.findAllByAtivoTrue();

        return funcionariosAtivos.stream().map(funcionarioMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarInativos() {
        List<Funcionario> funcionariosInativos = repository.findAllByAtivoFalse();

        return funcionariosInativos.stream().map(funcionarioMapper::toDto).toList();
    }

    private Funcionario buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Funcionario nâo cadastrado"));
    }

    private void verificarCpfCadastrado(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("funcionario ja possui cadastro");
        }
    }

    private void preencherEnderecoComViaCep(Funcionario funcionario) {

        funcionario.getEnderecos().forEach(e -> {

            if (e.getCep() != null && !e.getCep().isBlank()) {
                ViaCepResponseDTO viaCep = viaCepService.getViaCep(e.getCep());

                enderecoMapper.preencherComViaCep(viaCep, e);
            }
        });
    }

    private void atualizarEndereco(Funcionario funcionario, EnderecoUpdateRequestDTO request) {
        if (request == null) {
            return;
        }
        Endereco endereco = funcionario.getEnderecos().stream()
                .filter(e -> e.getId().equals(request.id()))
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado"));
        enderecoMapper.toUpdate(request, endereco);

        if (request.cep() != null && !request.cep().isBlank()) {

            ViaCepResponseDTO viaCep = viaCepService.getViaCep(request.cep());
            enderecoMapper.preencherComViaCep(viaCep, endereco);
        }
    }

    private void atualizarTelefones(Funcionario funcionario, List<TelefoneUpdateRequestDTO> requests) {
        if (requests == null) {
            return;
        }

        requests.forEach(request -> {
                Telefone telefone = funcionario.getTelefones()
                        .stream()
                        .filter(t -> t.getId().equals(request.id()))
                        .findFirst()
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Telefone não encontrado"));

                telefoneMapper.toUpdate(request, telefone);
            }
        );
    }
}

