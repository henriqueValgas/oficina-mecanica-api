package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.OrdemServicoRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrdemServicoMapper {

    @Mapping(target = "pessoa", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "funcionario", ignore = true)
    @Mapping(target = "itemPeca", ignore = true)
    @Mapping(target = "itemServico", ignore = true)
    @Mapping(target = "numeroOs", ignore = true)
    OrdemServico toEntity(OrdemServicoRequestDTO request);

    @Mapping(target = "pessoa", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "funcionario", ignore = true)
    @Mapping(target = "itemPeca", ignore = true)
    @Mapping(target = "itemServico", ignore = true)
    OrdemServico toUpdate(OrdemServicoRequestDTO request, @MappingTarget OrdemServico entity);

    OrdemServicoRequestDTO toDto(OrdemServico entity);
}
