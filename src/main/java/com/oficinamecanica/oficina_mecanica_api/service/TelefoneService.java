package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.exceptions.RegistroNaoEncontradoException;
import com.oficinamecanica.oficina_mecanica_api.mapper.TelefoneMapper;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Pessoa;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelefoneService {

    private final TelefoneMapper telefoneMapper;

    @Transactional
    public void atualizarTelefones(Pessoa pessoa, List<TelefoneUpdateRequestDTO> requests) {
        if (requests == null) {
            return;
        }

        requests.forEach(request -> {
                    Telefone telefone = pessoa.getTelefones()
                            .stream()
                            .filter(t -> t.getId().equals(request.id()))
                            .findFirst()
                            .orElseThrow(() -> new RegistroNaoEncontradoException("Telefone não encontrado"));

                    telefoneMapper.toUpdate(request, telefone);
                }
        );
    }
}
