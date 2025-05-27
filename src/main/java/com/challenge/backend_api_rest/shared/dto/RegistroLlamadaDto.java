package com.challenge.backend_api_rest.shared.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de una llamada registrada a la API")
public class RegistroLlamadaDto {

    @Schema(description = "Identificador único del registro", example = "1")
    private Long id;

    @Schema(description = "Fecha y hora de la llamada", example = "2023-05-08T10:15:30")
    private LocalDateTime fecha;

    @Schema(description = "Endpoint que fue llamado", example = "/api/calculadora/calcular")
    private String endpoint;

    @Schema(description = "Parámetros enviados en la llamada (formato JSON)", example = "{\"num1\":100.0,\"num2\":50.0}")
    private String parametros;

    @Schema(description = "Respuesta obtenida (formato JSON)", example = "{\"resultado\":150.0}", nullable = true)
    private String respuesta;

    @Schema(description = "Mensaje de error en caso de fallo", example = "División por cero", nullable = true)
    private String error;

    @Schema(description = "Indica si la llamada fue exitosa", example = "true")
    private Boolean exitoso;

    public RegistroLlamadaDto(Long id, LocalDateTime fecha, String endpoint, String parametros,
            String respuesta, String error, Boolean exitoso) {
        this.id = id;
        this.fecha = fecha;
        this.endpoint = endpoint;
        this.parametros = parametros;
        this.respuesta = respuesta;
        this.error = error;
        this.exitoso = exitoso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getExitoso() {
        return exitoso;
    }

    public void setExitoso(Boolean exitoso) {
        this.exitoso = exitoso;
    }

}