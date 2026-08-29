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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService {

    private final PessoaBuilder pessoaBuilder;
    private final PessoaJuridicaRepository repository;
    private final PessoaJuridicaMapper mapperPessoaJuridica;

    @Transactional
    public PessoaJuridicaResponseDTO salvarPessoaJuridica(PessoaJuridicaCreateRequestDTO request) {

        PessoaJuridica pessoaJuridica = mapperPessoaJuridica.toEntity(request);

        verificaCnpjCadastrado(pessoaJuridica.getCnpj());

        List<Endereco> enderecos = pessoaBuilder.buildEnderecos(request.enderecos());

        enderecos.forEach(pessoaJuridica::addEndereco);

        List<Telefone> telefones = pessoaBuilder.buildTelefones(request.telefones());

        telefones.forEach(pessoaJuridica::addTelefone);

        repository.save(pessoaJuridica);

        return mapperPessoaJuridica.toDTO(pessoaJuridica);
    }

    @Transactional
    public PessoaJuridicaResponseDTO atualizaPessoaJuridica(UUID id, PessoaJuridicaUpdateRequestDTO request) {

        PessoaJuridica pessoaJuridica = buscaClienteId(id);

        mapperPessoaJuridica.toUpdate(request, pessoaJuridica);

        if (request.endereco() != null) {
            pessoaBuilder.updateEndereco(request.endereco(), pessoaJuridica.getEnderecos());
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
    public void inativaPessoaJurica(UUID id) {

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

    @Transactional
    public PessoaJuridicaResponseDTO buscarPessoaJuridicaPorCnpj(@CNPJ String cnpj) {

        PessoaJuridica pessoaJuridica = buscarPessoaJuridicaCnpjEAtivo(cnpj);

        return mapperPessoaJuridica.toDTO(pessoaJuridica);
    }

    private void verificaCnpjCadastrado(String cnpj){
        if (repository.existsByCnpj(cnpj)) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }
    }

    private PessoaJuridica buscaClienteId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    public PessoaJuridica buscarPessoaJuridicaCnpjEAtivo(String cnpj) {
         return repository.findByCnpjAndAtivoTrue(cnpj)
                 .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
