package com.oficinamecanica.oficina_mecanica_api.integration.viacep;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final RestClient viaCepRestClient;

    public ViaCepResponseDTO getViaCep(String cep) {

        ViaCepResponseDTO response = viaCepRestClient.get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .body(ViaCepResponseDTO.class);

        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new RuntimeException("CEP não encontrado");
        }
        return response;
    }
}
