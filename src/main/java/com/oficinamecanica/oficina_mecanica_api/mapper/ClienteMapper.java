package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClienteRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClienteResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequestDto request);

    ClienteResponseDTO toDTO(Cliente cliente);

    void updateEntityFromDTO(ClienteRequestDto request, @MappingTarget Cliente cliente);
}
