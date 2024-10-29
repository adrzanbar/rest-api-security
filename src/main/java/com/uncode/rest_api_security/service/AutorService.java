package com.uncode.rest_api_security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.model.Autor;
import com.uncode.rest_api_security.repository.AutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService extends CrudService<Autor, Long> {

    private final AutorRepository repository;

    @Override
    protected JpaRepository<Autor, Long> getRepository() {
        return repository;
    }

    @Override
    protected void validate(Autor entity) throws ServiceException {
    }

}
