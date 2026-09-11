package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaCreateRequestDto;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PecaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PecaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Peca;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PecaMapper {

    Peca toEntity(PecaCreateRequestDto requestDto);

    Peca toUpdate(PecaUpdateRequestDTO request, @MappingTarget Peca entity);

    PecaResponseDTO toDTO(Peca entity);
}
