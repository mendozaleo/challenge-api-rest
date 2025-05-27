package com.challenge.backend_api_rest.domain;

import java.math.BigDecimal;

public interface PorcentajeCache {

    BigDecimal obtenerPorcentaje();

    void guardarPorcentaje(BigDecimal porcentaje);

    boolean tieneValorValido();
}
