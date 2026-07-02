package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.EnderecoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoRequestDTO request);

    EnderecoResponseDTO toDTO(Endereco entity);
}
