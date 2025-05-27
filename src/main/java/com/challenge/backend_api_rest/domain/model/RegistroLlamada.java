package com.challenge.backend_api_rest.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "registro_llamadas")
public class RegistroLlamada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private String endpoint;

    @Column(columnDefinition = "jsonb")
    private String parametros;

    private String respuesta;

    private String error;

    private Boolean exitoso;

    public RegistroLlamada() {

    }

    public RegistroLlamada(LocalDateTime fecha, String endpoint, String parametros,
            String respuesta, String error, Boolean exitoso) {
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

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getParametros() {
        return parametros;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public String getError() {
        return error;
    }

    public Boolean getExitoso() {
        return exitoso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setExitoso(Boolean exitoso) {
        this.exitoso = exitoso;
    }
}
