package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloCreateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ModeloUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ModeloResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Modelo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    Modelo toEntity(ModeloCreateResquestDTO request);

    void toUpdate(ModeloUpdateRequestDTO request, @MappingTarget Modelo entity);

    ModeloResponseDTO toDTO(Modelo entity);
}
