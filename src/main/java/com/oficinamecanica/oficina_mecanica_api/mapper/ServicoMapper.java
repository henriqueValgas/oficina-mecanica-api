package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ServicoUpdateResquestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ServicoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toEntity(ServicoCreateRequestDTO request);

    void toUpdate(ServicoUpdateResquestDTO request, @MappingTarget Servico entity);

    ServicoResponseDTO toDTO(Servico entity);
}
