package com.oficinamecanica.oficina_mecanica_api.mapper;

import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaCreateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO.ClientePessoaFisicaUpdateRequestDTO;
import com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO.ClientePessoaFisicaResponseDTO;
import com.oficinamecanica.oficina_mecanica_api.model.entity.ClientePessoaFisica;
import com.oficinamecanica.oficina_mecanica_api.model.entity.PessoaFisica;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
uses = {EnderecoMapper.class,
        TelefoneMapper.class
})
public interface ClientePessoaFisicaMapper {


    ClientePessoaFisica toEntity(ClientePessoaFisicaCreateRequestDTO request);

    @BeanMapping(
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
    )

    @Mapping(target = "enderecos", ignore = true)
    @Mapping(target = "telefones", ignore = true)
    void toUpdate(ClientePessoaFisicaUpdateRequestDTO request, @MappingTarget ClientePessoaFisica clientePessoaFisica);

    ClientePessoaFisicaResponseDTO toDTO(ClientePessoaFisica entity);

}
