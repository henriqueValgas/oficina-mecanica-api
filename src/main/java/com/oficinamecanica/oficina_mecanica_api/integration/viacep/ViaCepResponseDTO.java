package com.oficinamecanica.oficina_mecanica_api.integration.viacep;

public record ViaCepResponseDTO(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    Boolean erro

){
}
