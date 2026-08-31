package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ClientePessoaFisicaMapper {

    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    ClientePessoaFisica toEntity(ClientePessoaFisicaCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "enderecos", ignore = true)
    void toUpdate(ClientePessoaFisicaUpdateRequestDTO request, @MappingTarget ClientePessoaFisica clientePessoaFisica);

    ClientePessoaFisicaResponseDTO toDTO(ClientePessoaFisica entity);

}
