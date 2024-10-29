package com.uncode.rest_api_security.error;

public class NotFoundException extends Exception {

    public static final String MESSAGE = "No se encontró el recurso con el identificador ";
    public String id;

    public NotFoundException(String id) {
        super(MESSAGE + id);
        this.id = id;
    }

}
