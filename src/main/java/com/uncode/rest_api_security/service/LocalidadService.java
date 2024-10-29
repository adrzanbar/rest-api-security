package com.uncode.rest_api_security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.model.Localidad;
import com.uncode.rest_api_security.repository.LocalidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalidadService extends CrudService<Localidad, Long> {
    
    private final LocalidadRepository repository;
    
    @Override
    protected JpaRepository<Localidad, Long> getRepository() {
        return repository;
    }

    @Override
    protected void validate(Localidad entity) throws ServiceException {
        if (repository.existsByDenominacion(entity.getDenominacion())) {
            throw new ServiceException("Ya existe una localidad con la denominación " + entity.getDenominacion());
        }
    }

}
