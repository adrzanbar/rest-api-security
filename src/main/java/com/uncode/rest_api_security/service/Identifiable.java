package com.uncode.rest_api_security.service;

public interface Identifiable<T> {
    public T getId();

    public void setId(T id);
}
