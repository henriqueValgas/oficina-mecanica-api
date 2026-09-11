package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloCreateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ModeloResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ModeloMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Modelo;
import com.oficinamecanica.oficina_mecanica_api.repository.ModeloRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    private final ModeloMapper modeloMapper;

    @Transactional
    public ModeloResponseDTO salvar(@Valid ModeloCreateResquestDTO request) {

        Modelo modelo = modeloMapper.toEntity(request);

        return modeloMapper.toDTO(modelo);
    }

    @Transactional
    public ModeloResponseDTO atualizar(UUID id, @Valid ModeloUpdateRequestDTO request) {
        Modelo modelo = buscarPorId(id);

        modeloMapper.toUpdate(request,modelo);

        return modeloMapper.toDTO(modelo);
    }

    @Transactional
    public void inativar(UUID id){
        Modelo modelo = buscarPorId(id);
        if (!modelo.isAtivo()){
            throw new OperacaoInvalidaException("Modelo esta Inativado");
        }
        modelo.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id){
        Modelo modelo = buscarPorId(id);
        if (modelo.isAtivo()){
            throw new OperacaoInvalidaException("Modelo esta ativo");
        }
        modelo.setAtivo(true);
    }

    @Transactional(readOnly = true)
    public List<ModeloResponseDTO> listarAtivos(){
        List<Modelo> modelos = modeloRepository.findAllByAtivoTrue();

        return modelos.stream().map(modeloMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ModeloResponseDTO> listarInativos(){
        List<Modelo> modelos = modeloRepository.findAllByAtivoFalse();

        return modelos.stream().map(modeloMapper::toDTO).toList();
    }

    private Modelo buscarPorId(UUID id) {
        return modeloRepository
                .findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Modelo não encontrado"));
    }
}
