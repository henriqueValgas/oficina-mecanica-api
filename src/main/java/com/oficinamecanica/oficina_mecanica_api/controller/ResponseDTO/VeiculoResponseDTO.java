package com.oficinamecanica.oficina_mecanica_api.controller.ResponseDTO;

import com.oficinamecanica.oficina_mecanica_api.model.entity.Modelo;

import java.util.UUID;

public record VeiculoResponseDTO(

        UUID id,
        String placa,
        String renavan,
        String anoFabricacao,
        String anoModelo,
        Integer quilometragem,
        String cor,
        String combustivel,
        Modelo modelo
) {
}
