package com.challenge.backend_api_rest.shared.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Estructura estándar de respuesta para errores")
public class ErrorResponse {

    @Schema(description = "Código de estado HTTP", example = "400")
    private int status;
    @Schema(description = "Mensaje descriptivo del error", example = "Parámetros inválidos")
    private String mensaje;
    @Schema(description = "Fecha y hora del error", example = "2025-05-11T02:23:09.364Z")
    private LocalDateTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String mensaje, LocalDateTime timestamp) {
        this.status = status;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
