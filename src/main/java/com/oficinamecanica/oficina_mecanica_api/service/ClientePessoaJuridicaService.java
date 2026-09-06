package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.OperacaoInvalidaException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroDuplicadoException;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClientePessoaJuridicaMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaJuridica;
import com.oficinamecanica.oficina_mecanica_api.repository.ClientePessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientePessoaJuridicaService {

    private final ClientePessoaJuridicaRepository repository;
    private final ClientePessoaJuridicaMapper mapperPessoaJuridica;
    private final EnderecoService enderecoService;
    private final TelefoneService telefoneService;

    @Transactional
    public ClientePessoaJuridicaResponseDTO salvar(ClientePessoaJuridicaCreateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = mapperPessoaJuridica.toEntity(request);

        verificarCnpjCadastrado(clientePessoaJuridica.getCnpj());

        enderecoService.preencherEnderecoComViaCep(clientePessoaJuridica);

        clientePessoaJuridica.getEnderecos().forEach(e -> e.setPessoa(clientePessoaJuridica));

        clientePessoaJuridica.getTelefones().forEach(t -> t.setPessoa(clientePessoaJuridica));

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO atualizar(UUID id, ClientePessoaJuridicaUpdateRequestDTO request) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPorId(id);

        mapperPessoaJuridica.toUpdate(request, clientePessoaJuridica);

        enderecoService.atualizarEndereco(clientePessoaJuridica, request.enderecos());

        telefoneService.atualizarTelefones(clientePessoaJuridica, request.telefones());

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    @Transactional
    public void inativar(UUID id) {
        ClientePessoaJuridica clientePessoaJuridica = buscarPorId(id);
        if (!clientePessoaJuridica.isAtivo()) {
            throw new OperacaoInvalidaException("Cliente esta inativado");
        }
        clientePessoaJuridica.setAtivo(false);
    }

    @Transactional
    public void reativar(UUID id) {
        ClientePessoaJuridica clientePessoaJuridica = buscarPorId(id);
        if (clientePessoaJuridica.isAtivo()) {
            throw new OperacaoInvalidaException("Cliente esta ativo");
        }
        clientePessoaJuridica.setAtivo(true);
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarAtivos() {

        List<ClientePessoaJuridica> clientesAtivos = repository.findAllByAtivoTrue();

        return clientesAtivos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public List<ClientePessoaJuridicaResponseDTO> listarInativos() {

        List<ClientePessoaJuridica> clientesInativos = repository.findAllByAtivoFalse();

        return clientesInativos.stream().map(mapperPessoaJuridica::toDTO).toList();
    }

    @Transactional
    public ClientePessoaJuridicaResponseDTO buscarPorCnpj(@CNPJ String cnpj) {

        ClientePessoaJuridica clientePessoaJuridica = buscarPorCnpjAtivo(cnpj);

        return mapperPessoaJuridica.toDTO(clientePessoaJuridica);
    }

    private void verificarCnpjCadastrado(String cnpj) {
        if (repository.existsByCnpj(cnpj)) {
            throw new RegistroDuplicadoException("Cliente ja possui cadastro");
        }
    }

    private ClientePessoaJuridica buscarPorId(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }

    private ClientePessoaJuridica buscarPorCnpjAtivo(String cnpj) {
        return repository.findByCnpjAndAtivoTrue(cnpj)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Cliente não encontrado"));
    }
}
