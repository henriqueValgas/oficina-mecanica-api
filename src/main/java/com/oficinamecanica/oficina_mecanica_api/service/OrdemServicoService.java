package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ItemPecaRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.OrdemServicoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.OrdemServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.OrdemServicoMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.*;
import com.oficinamecanica.oficina_mecanica_api.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    private final PessoaService pessoaService;

    private final VeiculoService veiculoService;

    private final FuncionarioService funcionarioService;

    private final ServicoService servicoService;

    private final ItemPecaService itemPecaService;

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

    @Transactional
    private void addItemPeca(UUID ordemServicoID, ItemPecaRequestDTO request) {

        Peca peca = pecaService.buscarPorId(request.pecaId());
        OrdemServico ordemServico = buscarOrdemServico(ordemServicoID);

        ItemPeca itemPeca = itemPecaService.criar(peca, request.desconto(), request.quantidade(), request.observacoes());

        itemPeca.setOrdemServico(ordemServico);
        ordemServico.getItemPeca().add(itemPeca);
    }

    private OrdemServico buscarOrdemServico(UUID id) {
        return ordemServicoRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Ordem de serviço não encontrada"));
    }

    private String gerarNumeroOS(LocalDate dataEntrada) {

        Long sequencia = ordemServicoRepository.obterProximoNumeroOs();
        return String.format("OS-%d-%06d", dataEntrada.getYear(), sequencia);

    }


}
