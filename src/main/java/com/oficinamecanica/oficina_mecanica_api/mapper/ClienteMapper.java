package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.EnderecoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.TelefoneResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Cliente;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequestDto request);

    ClienteResponseDTO toDTO(Cliente cliente);

    TelefoneResponseDTO toDTO(Telefone telefone);

    EnderecoResponseDTO toDTO(Endereco endereco);

    void updateEntityFromDTO(ClienteRequestDto request, @MappingTarget Cliente cliente);
}
