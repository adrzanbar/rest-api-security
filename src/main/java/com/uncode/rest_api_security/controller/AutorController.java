package com.uncode.rest_api_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.rest_api_security.model.Autor;
import com.uncode.rest_api_security.service.AutorService;
import com.uncode.rest_api_security.service.CrudService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorController extends CrudController<Autor, Long> {
    
    private final AutorService service;
    
    @Override
    protected CrudService<Autor, Long> getService() {
        return service;
    }

}
