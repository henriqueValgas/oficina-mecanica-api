package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaJuridica;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientePessoaJuridicaMapper {

    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    ClientePessoaJuridica toEntity(ClientePessoaJuridicaCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    void toUpdate(ClientePessoaJuridicaUpdateRequestDTO request, @MappingTarget ClientePessoaJuridica entity);

    ClientePessoaJuridicaResponseDTO toDTO(ClientePessoaJuridica entity);

}
