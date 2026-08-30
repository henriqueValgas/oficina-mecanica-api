package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.TelefoneRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.TelefoneResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Telefone;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TelefoneMapper {

    Telefone toEntity(TelefoneRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pessoa", ignore = true)
    void toUpdate(TelefoneRequestDTO request, @MappingTarget Telefone entity);

    TelefoneResponseDTO toDto(Telefone entity);
}
