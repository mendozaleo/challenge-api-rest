package com.challenge.backend_api_rest.shared.exeption;

import org.springframework.http.HttpStatus;

public class CalculadoraException extends RuntimeException {

    private final HttpStatus status;

    public CalculadoraException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
