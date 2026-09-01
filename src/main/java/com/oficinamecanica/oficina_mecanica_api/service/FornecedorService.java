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
    public FornecedorResponseDTO salvar(@Valid FornecedorRequestDTO request) {

        Fornecedor fornecedor = mapper.toEntity(request);

        repository.save(fornecedor);

        return mapper.toDTO(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO atualizar(UUID id, FornecedorRequestDTO request) {

        Fornecedor fornecedor = buscarPorId(id);

        mapper.toUpdate(request, fornecedor);

        repository.save(fornecedor);

        return mapper.toDTO(fornecedor);
    }


    @Transactional
    public void inativar(UUID id) {

        Fornecedor fornecedor = buscarPorIdAtivo(id);

       fornecedor.setAtivo(false);
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarAtivos() {

        List<Fornecedor> listarAtivos = repository.findAllByAtivoTrue();

        return listarAtivos.stream().map(mapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarInativos(){

        List<Fornecedor> listarInativos = repository.findAllByAtivoFalse();

        return listarInativos.stream().map(mapper::toDTO).toList();
    }

    private Fornecedor buscarPorId(UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Fornecedor não encontrado"));

    }

    private Fornecedor buscarPorIdAtivo(UUID id){

        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Fornecedor não encontrado"));
    }

}
