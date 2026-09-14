package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ServicoMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Servico;
import com.oficinamecanica.oficina_mecanica_api.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    @Transactional
    public ServicoResponseDTO salvar(ServicoCreateRequestDTO request) {

        Servico servico = servicoMapper.toEntity(request);

        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public ServicoResponseDTO atualizar(UUID id, ServicoUpdateResquestDTO request){

        Servico servico = buscarPorId(id);

        servicoMapper.toUpdate(request, servico);

        return servicoMapper.toDTO(servico);
    }

    @Transactional
    public void inativar(UUID id){
        Servico servico = buscarPorId(id);
        if (!servico.isAtivo()) {
            throw new OperacaoInvalidaException("Serviço está inativado!");
        }
        servico.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id){
        Servico servico = buscarPorId(id);
        if(servico.isAtivo()) {
            throw new OperacaoInvalidaException("Serviço está ativo!");
        }
        servico.setAtivo(true);
    }


    @Transactional(readOnly = true)
    public List<ServicoResponseDTO> listar(){
        List<Servico> servicos = servicoRepository.findAllByAtivoTrue();

        return servicos.stream().map(servicoMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ServicoResponseDTO> listarInativos(){
        List<Servico> servicos = servicoRepository.findAllByAtivoFalse();

        return servicos.stream().map(servicoMapper::toDTO).toList();
    }


    private Servico buscarPorId(UUID id){
        return servicoRepository
                .findById(id)
                .orElseThrow(()-> new RegistroNaoEncontradoException("Serviço não encontrado"));
    }
}
