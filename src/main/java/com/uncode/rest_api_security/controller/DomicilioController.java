package com.uncode.rest_api_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uncode.rest_api_security.model.Domicilio;
import com.uncode.rest_api_security.service.CrudService;
import com.uncode.rest_api_security.service.DomicilioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/domicilios")
@RequiredArgsConstructor
public class DomicilioController extends CrudController<Domicilio, Long> {
    
    private final DomicilioService service;
    
    @Override
    protected CrudService<Domicilio, Long> getService() {
        return service;
    }

}
