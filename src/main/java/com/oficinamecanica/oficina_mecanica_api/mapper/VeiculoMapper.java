package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.VeiculoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.VeiculoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntity(VeiculoCreateRequestDTO request);

    void toUpdate(VeiculoUpdateRequestDTO request, @MappingTarget Veiculo entity);

    VeiculoResponseDTO toDTO(Veiculo entity);

}
