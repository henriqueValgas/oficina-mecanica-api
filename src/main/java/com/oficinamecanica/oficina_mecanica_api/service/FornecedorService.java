package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FornecedorRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FornecedorResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.FornecedorMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Fornecedor;
import com.oficinamecanica.oficina_mecanica_api.repository.FornecedorRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FornecedorService {

    private final FornecedorRepository repository;
    private final FornecedorMapper mapper;

    @Transactional
    public FornecedorResponseDTO salvarFornecedor(@Valid FornecedorRequestDTO request) {

        Fornecedor fornecedor = mapper.toEntity(request);

        repository.save(fornecedor);

        return mapper.toDTO(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO atualizaFornecedor(UUID id, FornecedorRequestDTO request) {

        Fornecedor fornecedor = buscarFornecedorPorId(id);

        mapper.toUpdate(request, fornecedor);

        repository.save(fornecedor);

        return mapper.toDTO(fornecedor);
    }


    @Transactional
    public void inativarFornecedor(UUID id) {

        Fornecedor fornecedor = buscarFornecedorPorIdEAtivo(id);

       fornecedor.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarFornecedorAtivos() {

        List<Fornecedor> listarFornecedorAtivo = repository.findAllByAtivoTrue();

        return listarFornecedorAtivo.stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarFornecedorInativo(){

        List<Fornecedor> listarFornecedorInativo = repository.findAllByAtivoFalse();

        return listarFornecedorInativo.stream().map(mapper::toDTO).toList();
    }

    private Fornecedor buscarFornecedorPorId(UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Fornecedor não encontrado"));

    }

    private Fornecedor buscarFornecedorPorIdEAtivo(UUID id){

        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Fornecedor não encontrado"));
    }

}
