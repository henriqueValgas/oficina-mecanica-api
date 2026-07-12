package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.builder.PessoaBuilder;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.PessoaJuridicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaJuridica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.PessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService {

    private final PessoaBuilder pessoaBuilder;
    private final PessoaJuridicaRepository repository;
    private final PessoaJuridicaMapper mapperPessoaJuridica;

    @Transactional
    public PessoaJuridicaResponseDTO salvarPessoaJuridica(PessoaJuridicaCreateRequestDTO request) {

        PessoaJuridica pessoaJuridica = mapperPessoaJuridica.toEntity(request);

        if (existeCliente(pessoaJuridica.getCnpj())) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }

        Endereco endereco = pessoaBuilder.buildEndereco(request.endereco());
        pessoaJuridica.setEndereco(endereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());

        telefones.forEach(pessoaJuridica::addTelefone);

        repository.save(pessoaJuridica);

        return mapperPessoaJuridica.toDTO(pessoaJuridica);
    }

    @Transactional
    public PessoaJuridicaResponseDTO atualizaPessoaJuridica(Long id, PessoaJuridicaUpdateRequestDTO request) {

        PessoaJuridica pessoaJuridica = buscaClienteId(id);

        mapperPessoaJuridica.toUpdate(request, pessoaJuridica);

        if (request.endereco() != null) {
            pessoaBuilder.updateEndereco(request.endereco(), pessoaJuridica.getEndereco());
        }

        if (request.telefones() != null) {
            pessoaJuridica.getTelefones().clear();

            for (TelefoneRequestDTO telefoneRequestDTO : request.telefones()) {
                Telefone telefone = new Telefone();

                telefone.setNumero(telefoneRequestDTO.numero());
                telefone.setTipo(telefoneRequestDTO.tipo());

                telefone.setPessoa(pessoaJuridica);

                pessoaJuridica.getTelefones().add(telefone);
            }
        }
        repository.save(pessoaJuridica);

        return mapperPessoaJuridica.toDTO(pessoaJuridica);
    }

    @Transactional
    public void inativaPessoaJurica(Long id) {

        PessoaJuridica pessoaJuridica = buscaClienteId(id);

        pessoaJuridica.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<PessoaJuridicaResponseDTO> listarPessoaJuridicaAtivos() {

        List<PessoaJuridica> listaPessoaJuridicaAtivos = repository.findAllByAtivoTrue();

        return listaPessoaJuridicaAtivos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<PessoaJuridicaResponseDTO> listarPessoaJuridicaInativos() {

        List<PessoaJuridica> listaPessoaJuridicaInativos = repository.findAllByAtivoFalse();

        return listaPessoaJuridicaInativos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    private boolean existeCliente(String cnpj) {
        return repository.existsByCnpj(cnpj);
    }

    private PessoaJuridica buscaClienteId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    @Transactional
    public PessoaJuridicaResponseDTO buscarPessoaJuridicaPorCnpj(@CNPJ String cnpj) {

        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaCnpjEAtivo(cnpj);

        return mapperPessoaJuridica.toDTO(pessoaJuridica);
    }

    public PessoaJuridica buscarPessoaJuridicaCnpjEAtivo(String cnpj) {
         return repository.findByCnpjAndAtivoTrue(cnpj)
                 .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
