package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.OrdemServicoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.OrdemServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.OrdemServico;
import com.oficinamecanica.oficina_mecanica_api.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    private final PessoaService  pessoaService;

    private final VeiculoService  veiculoService;

    private final FuncionarioService  funcionarioService;

    private final ServicoService servicoService;

    private final PecaService pecaService;

    public OrdemServicoResponseDTO salvarOrdemServico(OrdemServicoRequestDTO request) {

        return null;
    }



}
