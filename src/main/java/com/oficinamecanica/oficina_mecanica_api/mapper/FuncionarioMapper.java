package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.FuncionarioUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.FuncionarioResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.Funcionario;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
uses = {
        EnderecoMapper.class,
        TelefoneMapper.class
})
public interface FuncionarioMapper {

    Funcionario toEntity(FuncionarioCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )
    @Mapping(target = "enderecos", ignore = true)
    void toUpdate(FuncionarioUpdateRequestDTO request, @MappingTarget Funcionario entity);

    FuncionarioResponseDTO toDto(Funcionario entity);
}

