package com.oficinamecanica.oficina_mecanica_api.controller.RequestDTO;

public record OrdemServicoRequestDTO(
        String cpfCnpj,
        String placa,
        int quilometragemEntrada,
        String defeitoRelatado

) {
}
