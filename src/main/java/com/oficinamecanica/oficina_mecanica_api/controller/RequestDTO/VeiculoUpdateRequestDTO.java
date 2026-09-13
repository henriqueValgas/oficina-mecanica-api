package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Modelo;

import java.util.UUID;

public record VeiculoUpdateRequestDTO(

        UUID id,
        String placa,
        String renavan,
        String anoFabricacao,
        String anoModelo,
        Integer quilometragem,
        String cor,
        String combustivel,
        UUID modelo_id
) {
}
