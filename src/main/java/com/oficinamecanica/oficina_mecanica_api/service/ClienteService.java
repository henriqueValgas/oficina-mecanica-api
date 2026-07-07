package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.integration.viacep.ViaCepService;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import com.oficinamecanica.oficina_mecanica_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;
    private final ViaCepService viaCepService;



    public Telefone buildTelefone(TelefoneRequestDTO request) {

        Telefone telefone = new Telefone();

        telefone.setNumero(request.numero());
        telefone.setTipo(request.tipo());

        return telefone;
    }

    public List<Telefone> buildTelefones(List<TelefoneRequestDTO> request) {

        List<Telefone> telefones = new ArrayList<>();

        for (TelefoneRequestDTO requestDTO : request) {
            telefones.add(buildTelefone(requestDTO));
        }
        return telefones;
    }

    public Endereco buildEndereco(EnderecoRequestDTO request) {

        String cep = request.cep();
        Endereco endereco = new Endereco();

        if (cep != null && !cep.isBlank()) {

            ViaCepResponseDTO viaCep = viaCepService.getViaCep(cep);
            endereco.setRua(viaCep.logradouro());

            endereco.setNumero(request.numero());

            endereco.setCep(viaCep.cep());
            endereco.setBairro(viaCep.bairro());
            endereco.setCidade(viaCep.localidade());
            endereco.setEstado(viaCep.uf());

        } else {

            endereco.setRua(request.rua());
            endereco.setNumero(request.numero());
            endereco.setCep(request.cep());
            endereco.setBairro(request.bairro());
            endereco.setCidade(request.cidade());
            endereco.setEstado(request.estado());

        }

        return endereco;
    }
}

