package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepService;
import com.oficinamecanica.oficina_mecanica_api.mapper.EnderecoMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Pessoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final ViaCepService viaCepService;

    @Transactional
    public void preencherEnderecoComViaCep(Pessoa pessoa) {

        pessoa.getEnderecos().forEach(e -> {

            if (e.getCep() != null && !e.getCep().isBlank()) {
                ViaCepResponseDTO viaCep = viaCepService.getViaCep(e.getCep());

                enderecoMapper.preencherComViaCep(viaCep, e);
            }
        });
    }
    @Transactional
    public void atualizarEndereco(Pessoa pessoa, EnderecoUpdateRequestDTO request) {
        if (request == null) {
            return;
        }
        Endereco endereco = pessoa.getEnderecos().stream()
                .filter(e -> e.getId().equals(request.id()))
                .findFirst()
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado"));
        enderecoMapper.toUpdate(request, endereco);

        if (request.cep() != null && !request.cep().isBlank()) {

            ViaCepResponseDTO viaCep = viaCepService.getViaCep(request.cep());
            enderecoMapper.preencherComViaCep(viaCep, endereco);
        }
    }
}
