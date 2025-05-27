package com.challenge.backend_api_rest.infrastructure;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.challenge.backend_api_rest.domain.PorcentajeCache;

@Component
public class MemoriaPorcentajeCache implements PorcentajeCache {

    private BigDecimal porcentajeCacheado = null;
    private long timestampCache = 0;
    private static final long TIEMPO_EXPIRACION_CACHE = 30 * 60 * 1000;

    @Override
    public BigDecimal obtenerPorcentaje() {
        return porcentajeCacheado;
    }

    @Override
    public void guardarPorcentaje(BigDecimal porcentaje) {
        this.porcentajeCacheado = porcentaje;
        this.timestampCache = System.currentTimeMillis();
    }

    @Override
    public boolean tieneValorValido() {
        if (porcentajeCacheado == null) {
            return false;
        }
        long tiempoActual = System.currentTimeMillis();
        return (tiempoActual - timestampCache) < TIEMPO_EXPIRACION_CACHE;
    }
}
