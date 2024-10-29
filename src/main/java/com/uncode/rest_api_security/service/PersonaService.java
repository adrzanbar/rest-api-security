package com.uncode.rest_api_security.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.model.Persona;
import com.uncode.rest_api_security.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService extends CrudService<Persona, Long> {

    private final PersonaRepository repository;
    
    @Override
    protected JpaRepository<Persona, Long> getRepository() {
        return repository;
    }

    @Override
    protected void validate(Persona entity) throws ServiceException {
        if (repository.existsByDni(entity.getDni())) {
            throw new ServiceException("Ya existe una persona con el DNI " + entity.getDni());
        }
    }


}
