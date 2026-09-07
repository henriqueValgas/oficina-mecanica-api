package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.MarcaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.MarcaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Marca;
import com.oficinamecanica.oficina_mecanica_api.repository.MarcaRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public MarcaResponseDTO atualizar(UUID id, @Valid MarcaUpdateResquestDTO request) {

        Marca marca = buscarPorIdAndAtivo(id);

        marcaMapper.toUpdate(request,marca);

        return marcaMapper.toDTO(marca);
    }

    @Transactional
    public void inativar(UUID id) {
        Marca marca = buscarPorId(id);
        if(!marca.isAtivo()){
            throw new OperacaoInvalidaException("cadastro já está inativo");
        }
        marca.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id){
        Marca marca = buscarPorId(id);
        if (marca.isAtivo()){
            throw new OperacaoInvalidaException("cadastro está ativo");
        }
        marca.setAtivo(true);
    }

    @Transactional
    public MarcaResponseDTO buscarPorNome(String nome) {

        Marca marca = buscarPorNomeAtivo(nome);

        return marcaMapper.toDTO(marca);
    }

    @Transactional
    public MarcaResponseDTO buscarPorNomeInativo(String nome){
        Marca marca = marcaRepository.findByNomeAndAtivoFalse(nome)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Marca inativa não encontrada"));

        return marcaMapper.toDTO(marca);
    }

    @Transactional(readOnly = true)
    public List<MarcaResponseDTO> listarAtivas() {

        List<Marca> marcas = marcaRepository.findAllByAtivoTrue();

        return marcas.stream().map(marcaMapper::toDTO).toList();
    }

   @Transactional(readOnly = true)
    public List<MarcaResponseDTO> listarInativas() {
        List<Marca> marcasInativas = marcaRepository.findAllByAtivoFalse();

        return marcasInativas.stream().map(marcaMapper::toDTO).toList();
    }

    //Verificar diagrama de classe para garantir unissidade de id com nome marca verificar constraint do BD
    private void verificaMarcaExiste(String nome) {
        marcaRepository.existsByNome(nome)
                .orElseThrow(()-> new RegistroDuplicadoException("Marca ja cadastrada"));
    }

    private Marca buscarPorId(UUID id) {
        return marcaRepository.findById(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Registro não encontrado"));
    }

    private Marca buscarPorIdAndAtivo(UUID id){
        return marcaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Marca não encontrada"));
    }

    private Marca buscarPorNomeAtivo(String nome){
        return marcaRepository.findByNomeAndAtivoTrue(nome)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Marca não cadastrada"));
    }
}
