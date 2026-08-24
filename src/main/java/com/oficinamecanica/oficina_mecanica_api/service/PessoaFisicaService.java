package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.builder.PessoaBuilder;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.PessoaFisicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.PessoaFisicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

    private final PessoaBuilder pessoaBuilder;
    private final PessoaFisicaRepository repository;
    private final PessoaFisicaMapper pessoaFisicaMapper;

    @Transactional
    public PessoaFisicaResponseDTO salvarPessoaFisica(PessoaFisicaCreateRequestDTO request) {

        PessoaFisica pessoaFisica = pessoaFisicaMapper.toEntity(request);

        verificaCpfCadastrado(pessoaFisica.getCpf());

        List<Endereco> enderecos = pessoaBuilder.buildEnderecos(request.enderecos());
        enderecos.forEach(pessoaFisica::addEndereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());
        telefones.forEach(pessoaFisica::addTelefone);

        repository.save(pessoaFisica);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    @Transactional
    public PessoaFisicaResponseDTO atualizaPessoaFisica(UUID id, PessoaFisicaUpdateRequestDTO request) {

        PessoaFisica pessoaFisica = buscaClienteId(id);

        pessoaFisicaMapper.toUpdate(request, pessoaFisica);

        if (request.endereco() != null) {
            pessoaBuilder.updateEndereco(request.endereco(), pessoaFisica.getEnderecos());
        }

        if (request.telefones() != null) {
            pessoaFisica.getTelefones().clear();

            for (TelefoneRequestDTO telefoneRequestDTO : request.telefones()) {
                Telefone telefone = new Telefone();

                telefone.setNumero(telefoneRequestDTO.numero());
                telefone.setTipo(telefoneRequestDTO.tipo());
                telefone.setPessoa(pessoaFisica);

                pessoaFisica.getTelefones().add(telefone);
            }
        }
        repository.save(pessoaFisica);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    @Transactional
    public void inativarPessoaFisica(UUID id) {

        PessoaFisica pessoaFisica = buscaClienteId(id);

        pessoaFisica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<PessoaFisicaResponseDTO> listarPessoaFisicaAtiva() {

        List<PessoaFisica> listaPessoas = repository.findAllByAtivoTrue();
        return listaPessoas.stream().map(pessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<PessoaFisicaResponseDTO> listarPessoaFisicaInativa() {

        List<PessoaFisica> listaInativas = repository.findAllByAtivoFalse();
        return listaInativas.stream().map(pessoaFisicaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public PessoaFisicaResponseDTO buscarClientePessoaFisicaPorCpf(String cpf) {

        PessoaFisica pessoaFisica = buscaClientePorCpfEAtivo(cpf);

        return pessoaFisicaMapper.toDTO(pessoaFisica);
    }

    private void verificaCpfCadastrado(String cpf) {
        if (repository.existsByCpf(cpf)) {
            throw new RegistroDuplicadoException("Cpf ja cadastrado");
        }
    }

    private PessoaFisica buscaClientePorCpfEAtivo(String cpf) {

        return repository.findByCpfAndAtivoTrue(cpf)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private PessoaFisica buscaClienteId(UUID id) {

        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

}
