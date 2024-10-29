package com.uncode.rest_api_security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uncode.rest_api_security.error.NotFoundException;
import com.uncode.rest_api_security.error.ServiceException;

import jakarta.transaction.Transactional;

public abstract class CrudService<E extends Identifiable<ID>, ID> {

    protected abstract JpaRepository<E, ID> getRepository();

    protected abstract void validate(E entity) throws ServiceException;

    @Transactional
    public E create(E entity) throws ServiceException {
        validate(entity);
        entity.setId(null);
        return getRepository().save(entity);
    }

    public Page<E> read(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public E read(ID id) throws NotFoundException {
        return getRepository().findById(id).orElseThrow(
                () -> new NotFoundException(id.toString()));
    }

    @Transactional
    public E update(E entity) throws ServiceException, NotFoundException {
        if (getRepository().existsById(entity.getId())) {
            validate(entity);
            return getRepository().save(entity);
        } else {
            throw new NotFoundException(entity.getId().toString());
        }
    }

    @Transactional
    public void delete(ID id) throws NotFoundException {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
        } else {
            throw new NotFoundException(id.toString());
        }
    }

}
