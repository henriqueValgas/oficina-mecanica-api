package com.oficinamecanica.oficina_mecanica_api.service;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.mapper.ClienteMapper;
import com.oficinamecanica.oficina_mecanica_api.model.Cliente;
import com.oficinamecanica.oficina_mecanica_api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDto request) {
            Cliente cliente = ClienteMapper.toEntity(request);

            Cliente saved = clienteRepository.save(cliente);

            return ClienteMapper.toDTO(saved);
    }

}
