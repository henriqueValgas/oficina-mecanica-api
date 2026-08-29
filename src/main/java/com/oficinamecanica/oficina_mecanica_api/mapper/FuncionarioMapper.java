package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    Funcionario toEntity(FuncionarioCreateRequestDTO request);

    void toUpdate(FuncionarioUpdateRequestDTO request, @MappingTarget Funcionario entity);

    FuncionarioResponseDTO toDto(Funcionario entity);

}
