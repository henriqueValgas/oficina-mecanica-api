package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.MarcaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.MarcaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Marca;
import com.oficinamecanica.oficina_mecanica_api.repository.MarcaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Transactional
    public MarcaResponseDTO salvar(MarcaCreateRequestDTO request){

        Marca marca = marcaMapper.toEntity(request);

        verificaMarcaExiste(request.nome());

        return marcaMapper.toDTO(marca);
    }

    @Transactional
    public MarcaResponseDTO atualiza(UUID id, @Valid MarcaUpdateResquestDTO request) {

        Marca marca = buscarPorId(id);

        marcaMapper.toUpdate(request,marca);

        return marcaMapper.toDTO(marca);
    }

    //Verificar diagrama de classe para garantir unissidade de id com nome marca verificar constraint do BD
    private void verificaMarcaExiste(String nome) {
        marcaRepository.existsByNome(nome)
                .orElseThrow(()-> new RegistroDuplicadoException("Marca ja cadastrada"));
    }

    private Marca buscarPorId(UUID id) {
        return marcaRepository
                .findById(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Registro não encontrado"));
    }
}
