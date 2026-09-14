package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaCreateRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PecaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.PecaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Peca;
import com.oficinamecanica.oficina_mecanica_api.repository.PecaRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PecaService {

    private final PecaRepository pecaRepository;
    private final PecaMapper pecaMapper;

    @Transactional
    public PecaResponseDTO salvar(PecaCreateRequestDto request) {

        Peca peca = pecaMapper.toEntity(request);

        return pecaMapper.toDTO(peca);
    }

    @PatchMapping("/{id}")
    public PecaResponseDTO atualiza(UUID id, @Valid PecaUpdateRequestDTO request) {

        Peca peca = buscarPorId(id);

        pecaMapper.toUpdate(request, peca);

        return pecaMapper.toDTO(peca);
    }

    @Transactional
    public void inativar(UUID id) {
        Peca peca = buscarPorId(id);
        if (!peca.isAtivo()){
            throw new OperacaoInvalidaException("Peça está inativada");
        }
        peca.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id) {
        Peca peca = buscarPorId(id);
        if (peca.isAtivo()){
            throw new OperacaoInvalidaException("Peça está ativa");
        }
        peca.setAtivo(true);
    }

    @Transactional(readOnly = true)
    public List<PecaResponseDTO> listar() {

        List<Peca> pecas = pecaRepository.findAllByAtivoTrue();

        return  pecas.stream().map(pecaMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<PecaResponseDTO> listarInativas() {
        List<Peca> pecasInativas =  pecaRepository.findAllByAtivoFalse();

        return  pecasInativas.stream().map(pecaMapper::toDTO).toList();
    }

    private Peca buscarPorId(UUID id) {

        return pecaRepository
                .findByIdAndAtivoTrue(id)
                .orElseThrow(()->new RegistroNaoEncontradoException("Peca não encontrada"));
    }
}
