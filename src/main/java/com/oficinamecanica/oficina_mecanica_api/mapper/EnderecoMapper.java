package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.EnderecoUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.EnderecoResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Endereco;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    void toUpdate(EnderecoUpdateRequestDTO request, @MappingTarget Endereco entity);

    EnderecoResponseDTO toDTO(Endereco entity);
}
