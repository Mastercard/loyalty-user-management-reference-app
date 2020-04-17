package com.mastercard.developer.exception;

import org.openapitools.client.model.Errors;

public class ServiceException extends Exception {

    private final transient Errors errors = new Errors();

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Errors serviceErrors) {
        super(message);
        errors.setErrors(serviceErrors.getErrors());
    }

    public Errors getServiceErrors() {
        return errors;
    }
}
