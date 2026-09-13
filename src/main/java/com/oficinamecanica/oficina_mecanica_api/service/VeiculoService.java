package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.VeiculoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.VeiculoMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Veiculo;
import com.oficinamecanica.oficina_mecanica_api.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;


    @Transactional
    public VeiculoResponseDTO salvar(VeiculoCreateRequestDTO request) {

        Veiculo veiculo = veiculoMapper.toEntity(request);

        verficaVeiculoCadastrado(veiculo.getId());

        return veiculoMapper.toDTO(veiculo);
    }

    @Transactional
    public VeiculoResponseDTO atualizar(UUID id, @Valid VeiculoUpdateRequestDTO request) {

        Veiculo veiculo = buscarPorId(id);

        veiculoMapper.toUpdate(request, veiculo);

        return veiculoMapper.toDTO(veiculo);
    }

    @Transactional
    public void inativar(UUID id) {

        if(!veiculoRepository.findByIdAndAtivoTrue(id)){

            throw new OperacaoInvalidaException("Veiculo está inativado");
        }
        Veiculo veiculo  = buscarPorId(id);
        veiculo.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id){
        if(veiculoRepository.findByIdAndAtivoTrue(id)){
            throw new OperacaoInvalidaException("Veiculo está ativado");
        }
        Veiculo veiculo  = buscarPorId(id);
        veiculo.setAtivo(true);
    }

    @Transactional()
    public List<VeiculoResponseDTO> listar() {
        List<Veiculo> veiculos = veiculoRepository.findAllByAtivoTrue();

        return veiculos.stream().map(veiculoMapper::toDTO).toList();
    }

    @Transactional
    public List<VeiculoResponseDTO> listarInativos() {
        List<Veiculo> veiculos = veiculoRepository.findAllByAtivoFalse();

        return veiculos.stream().map(veiculoMapper::toDTO).toList();
    }

    private Veiculo buscarPorId(UUID id) {
        return veiculoRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Registro não encontrado"));
    }

    private void verficaVeiculoCadastrado(UUID id) {
        if(veiculoRepository.existsById(id)){
            throw new RegistroDuplicadoException("Veiculo já cadastrado");
        }
    }

}
