package com.uncode.rest_api_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.rest_api_security.model.Libro;
import com.uncode.rest_api_security.service.CrudService;
import com.uncode.rest_api_security.service.LibroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController extends CrudController<Libro, Long> {

    private final LibroService service;

    @Override
    protected CrudService<Libro, Long> getService() {
        return service;
    }

}
