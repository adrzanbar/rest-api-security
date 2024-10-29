package com.uncode.rest_api_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.rest_api_security.model.Persona;
import com.uncode.rest_api_security.service.CrudService;
import com.uncode.rest_api_security.service.PersonaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController extends CrudController<Persona, Long>{

    private final PersonaService service;

    @Override
    protected CrudService<Persona, Long> getService() {
        return service;
    }

}
