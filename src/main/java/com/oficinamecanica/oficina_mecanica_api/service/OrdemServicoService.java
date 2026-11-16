package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.OrdemServicoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.OrdemServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.mapper.OrdemServicoMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import com.oficinamecanica.oficina_mecanica_api.model.entity.OrdemServico;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Pessoa;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Veiculo;
import com.oficinamecanica.oficina_mecanica_api.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    private final PessoaService  pessoaService;

    private final VeiculoService  veiculoService;

    private final FuncionarioService  funcionarioService;

    private final ServicoService servicoService;

    private final PecaService pecaService;

    private final OrdemServicoMapper ordemServicoMapper;

    @Transactional
    public OrdemServicoResponseDTO salvar(OrdemServicoRequestDTO request) {

        OrdemServico ordemServico = ordemServicoMapper.toEntity(request);

        Pessoa cliente = pessoaService.buscaPorDocumento(request.cpfCnpj());

        Veiculo veiculo = veiculoService.buscarPorPlaca(request.placa());

        Funcionario funcionario = funcionarioService.buscarEntidadePorMatricula(request.matricula());

        ordemServico.setPessoa(cliente);
        ordemServico.setVeiculo(veiculo);
        ordemServico.setFuncionario(funcionario);
        ordemServico.setDataEntrada(LocalDate.now());
        ordemServico.setNumeroOs(gerarNumeroOS(ordemServico.getDataEntrada()));

        ordemServicoRepository.save(ordemServico);

        return ordemServicoMapper.toDto(ordemServico);
    }


    private String gerarNumeroOS(LocalDate dataEntrada) {

        Long sequencia = ordemServicoRepository.obterProximoNumeroOs();

        return String.format("OS-%d-%06d", dataEntrada.getYear(), sequencia);

    }
}
