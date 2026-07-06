package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.PessoaJuridicaRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.PessoaJuridicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaJuridica;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaJuridicaMapper {

    PessoaJuridica toEntity(PessoaJuridicaRequestDTO request);

    PessoaJuridicaResponseDTO toDTO(PessoaJuridica entity);

}
