package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaFisicaRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper {

    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    PessoaFisica toEntity(PessoaFisicaRequestDTO request);

    PessoaFisicaResponseDTO toDTO(PessoaFisica entity);

}
