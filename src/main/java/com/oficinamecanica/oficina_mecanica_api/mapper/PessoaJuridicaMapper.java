package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaJuridica;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PessoaJuridicaMapper {

    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    PessoaJuridica toEntity(PessoaJuridicaCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    void toUpdate(PessoaJuridicaUpdateRequestDTO request, @MappingTarget PessoaJuridica entity);

    PessoaJuridicaResponseDTO toDTO(PessoaJuridica entity);

}
