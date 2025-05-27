package com.challenge.backend_api_rest.interfaces.rest.dto;

import java.math.BigDecimal;

public class ResultadoResponse {

    private BigDecimal resultado;

    public ResultadoResponse() {
    }

    public ResultadoResponse(BigDecimal resultado) {
        this.resultado = resultado;
    }

    public BigDecimal getResultado() {
        return resultado;
    }

    public void setResultado(BigDecimal resultado) {
        this.resultado = resultado;
    }
}
