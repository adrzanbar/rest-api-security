package com.uncode.rest_api_security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.model.Domicilio;
import com.uncode.rest_api_security.repository.DomicilioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DomicilioService extends CrudService<Domicilio, Long> {

    private final DomicilioRepository repository;

    @Override
    protected JpaRepository<Domicilio, Long> getRepository() {
        return repository;
    }

    @Override
    protected void validate(Domicilio entity) throws ServiceException {
        if (repository.existsByCalleAndNumeroAndLocalidad(entity.getCalle(), entity.getNumero(), entity.getLocalidad())) {
            throw new ServiceException("Ya existe un domicilio con la misma calle, número y localidad");
        }
    }

}
