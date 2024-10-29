package com.uncode.rest_api_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.rest_api_security.model.Localidad;
import com.uncode.rest_api_security.service.CrudService;
import com.uncode.rest_api_security.service.LocalidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/localidades")
@RequiredArgsConstructor
public class LocallidadController extends CrudController<Localidad, Long> {

    private final LocalidadService service;

    @Override
    protected CrudService<Localidad, Long> getService() {
        return service;
    }

}
