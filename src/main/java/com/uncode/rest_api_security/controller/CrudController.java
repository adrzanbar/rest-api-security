package com.uncode.rest_api_security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uncode.rest_api_security.error.NotFoundException;
import com.uncode.rest_api_security.error.ServiceException;
import com.uncode.rest_api_security.service.CrudService;
import com.uncode.rest_api_security.service.Identifiable;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

public abstract class CrudController<E extends Identifiable<ID>, ID> {

    protected abstract CrudService<E, ID> getService();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public E post(@Valid @RequestBody E entity) throws ServiceException {
        return getService().create(entity);
    }

    @GetMapping
    public Page<E> get(Pageable pageable) {
        return getService().read(pageable);
    }

    @GetMapping("/{id}")
    public E get(ID id) throws NotFoundException {
        return getService().read(id);
    }

    @PutMapping("/{id}")
    public E put(ID id, @Valid @RequestBody E entity) throws ServiceException, NotFoundException {
        entity.setId(id);
        return getService().update(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(ID id) throws NotFoundException {
        getService().delete(id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, String>();
        e.getBindingResult().getFieldErrors().forEach(
                (FieldError fe) -> errors.put(fe.getField(), fe.getDefaultMessage()));
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Los datos ingresados no son válidos");
        response.put("fieldErrors", errors);
        return response;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleServiceException(ServiceException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("error", "Error de servicio");
        response.put("message", e.getMessage());
        return response;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFoundException(NotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Not Found");
        response.put("message", "The requested resource was not found.");
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Error de validación");
        response.put("message", e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        return e.getMessage();
    }

}
