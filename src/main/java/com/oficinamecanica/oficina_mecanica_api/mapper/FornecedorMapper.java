package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FornecedorRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FornecedorResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    Fornecedor toEntity(FornecedorRequestDTO request);

    Fornecedor toUpdate(FornecedorRequestDTO request, @MappingTarget Fornecedor entity);

    FornecedorResponseDTO toDTO(Fornecedor entity);
}
