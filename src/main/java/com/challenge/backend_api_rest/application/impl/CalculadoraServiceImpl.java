package com.challenge.backend_api_rest.application.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.challenge.backend_api_rest.application.service.CalculadoraService;
import com.challenge.backend_api_rest.domain.PorcentajeCache;
import com.challenge.backend_api_rest.domain.PorcentajeProvider;
import com.challenge.backend_api_rest.shared.exeption.ServicioExternoNoDisponibleException;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{
    
    private final PorcentajeProvider porcentajeProvider;
    private final PorcentajeCache porcentajeCache;

    public CalculadoraServiceImpl(PorcentajeProvider porcentajeProvider, PorcentajeCache porcentajeCache) {
        this.porcentajeProvider = porcentajeProvider;
        this.porcentajeCache = porcentajeCache;
    }

    @Override
    public BigDecimal calcularResultado(BigDecimal num1, BigDecimal num2) {
        BigDecimal suma = num1.add(num2);
        BigDecimal porcentaje = obtenerPorcentajeConCache();

        if (porcentaje.compareTo(BigDecimal.ZERO) == 0) {
            return suma.setScale(2, RoundingMode.HALF_UP);
        }

        BigDecimal factor = BigDecimal.ONE.add(porcentaje);
        return suma.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal obtenerPorcentajeConCache() {

        if (porcentajeCache.tieneValorValido()) {
            return porcentajeCache.obtenerPorcentaje();
        }

        try {
            BigDecimal nuevoPorcentaje = porcentajeProvider.obtenerPorcentaje();
            porcentajeCache.guardarPorcentaje(nuevoPorcentaje);
            return nuevoPorcentaje;
        } catch (Exception e) {

            BigDecimal valorCacheado = porcentajeCache.obtenerPorcentaje();
            if (valorCacheado != null) {
                return valorCacheado;
            }

            throw new ServicioExternoNoDisponibleException("No se pudo obtener el porcentaje y no hay valor en caché.");
        }
    }
}
