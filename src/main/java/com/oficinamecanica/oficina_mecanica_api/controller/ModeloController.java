package com.oficinamecanica.oficina_mecanica_api.controller;

import com.oficinamecanica.oficina_mecanica_api.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modelo")
@RequiredArgsConstructor
public class ModeloController implements ControllerUriSupport {

    private final ModeloService modeloService;


}
