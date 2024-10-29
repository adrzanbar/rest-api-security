package com.uncode.rest_api_security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.model.Libro;
import com.uncode.rest_api_security.repository.LibroRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibroService extends CrudService<Libro, Long> {

    private final LibroRepository repository;

    @Override
    protected JpaRepository<Libro, Long> getRepository() {
        return repository;
    }

    @Override
    protected void validate(Libro entity) throws ServiceException {
    }

}
