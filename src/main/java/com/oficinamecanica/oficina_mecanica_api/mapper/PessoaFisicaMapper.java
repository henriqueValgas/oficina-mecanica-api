package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper {

    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    PessoaFisica toEntity(PessoaFisicaCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    void toUpdate(PessoaFisicaUpdateRequestDTO request, @MappingTarget PessoaFisica pessoaFisica);

    PessoaFisicaResponseDTO toDTO(PessoaFisica entity);

}
