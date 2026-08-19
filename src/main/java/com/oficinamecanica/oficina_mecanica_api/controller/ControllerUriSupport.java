package com.oficinamecanica.oficina_mecanica_api.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface ControllerUriSupport {

    default URI buildLocationUri(UUID id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
