package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.MarcaUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.MarcaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MarcaMapper {

    Marca toEntity(MarcaCreateRequestDTO requestDTO);

    void toUpdate(MarcaUpdateResquestDTO resquest, @MappingTarget Marca entity);

    MarcaResponseDTO toDTO(Marca marca);

}
