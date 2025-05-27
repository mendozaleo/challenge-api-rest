package com.challenge.backend_api_rest.shared.exeption;

public class ServicioExternoNoDisponibleException extends RuntimeException {

    public ServicioExternoNoDisponibleException(String message) {
        super(message);
    }
}
