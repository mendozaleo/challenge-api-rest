package com.challenge.backend_api_rest.application.impl;


import com.challenge.backend_api_rest.domain.PorcentajeCache;
import com.challenge.backend_api_rest.domain.PorcentajeProvider;
import com.challenge.backend_api_rest.shared.exeption.ServicioExternoNoDisponibleException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalculadoraServiceImplTest {

    private PorcentajeProvider porcentajeProvider;
    private PorcentajeCache porcentajeCache;
    private CalculadoraServiceImpl calculadoraService;

    @BeforeEach
    void setUp() {
        porcentajeProvider = mock(PorcentajeProvider.class);
        porcentajeCache = mock(PorcentajeCache.class);
        calculadoraService = new CalculadoraServiceImpl(porcentajeProvider, porcentajeCache);
    }

    @Test
    void testCalcularResultado_ConPorcentajeDesdeCache() {
        when(porcentajeCache.tieneValorValido()).thenReturn(true);
        when(porcentajeCache.obtenerPorcentaje()).thenReturn(new BigDecimal("0.10"));

        BigDecimal resultado = calculadoraService.calcularResultado(
                new BigDecimal("100.00"), new BigDecimal("50.00"));

        // (100 + 50) * 1.10 = 165.00
        assertEquals(new BigDecimal("165.00"), resultado);
    }

    @Test
    void testCalcularResultado_ConPorcentajeDesdeProvider() {
        when(porcentajeCache.tieneValorValido()).thenReturn(false);
        when(porcentajeProvider.obtenerPorcentaje()).thenReturn(new BigDecimal("0.20"));

        BigDecimal resultado = calculadoraService.calcularResultado(
                new BigDecimal("100.00"), new BigDecimal("50.00"));

        verify(porcentajeCache).guardarPorcentaje(new BigDecimal("0.20"));
        assertEquals(new BigDecimal("180.00"), resultado); // (100+50)*1.20
    }

    @Test
    void testCalcularResultado_ProveedorFallaPeroCacheTieneValor() {
        when(porcentajeCache.tieneValorValido()).thenReturn(false);
        when(porcentajeProvider.obtenerPorcentaje()).thenThrow(new RuntimeException("Error externo"));
        when(porcentajeCache.obtenerPorcentaje()).thenReturn(new BigDecimal("0.05"));

        BigDecimal resultado = calculadoraService.calcularResultado(
                new BigDecimal("100"), new BigDecimal("100"));

        assertEquals(new BigDecimal("210.00"), resultado); // (100+100)*1.05
    }

    @Test
    void testCalcularResultado_ProveedorFallaYSinValorEnCache() {
        when(porcentajeCache.tieneValorValido()).thenReturn(false);
        when(porcentajeProvider.obtenerPorcentaje()).thenThrow(new RuntimeException("Error externo"));
        when(porcentajeCache.obtenerPorcentaje()).thenReturn(null);

        ServicioExternoNoDisponibleException exception = assertThrows(
                ServicioExternoNoDisponibleException.class,
                () -> calculadoraService.calcularResultado(new BigDecimal("10"), new BigDecimal("20")));

        assertEquals("No se pudo obtener el porcentaje y no hay valor en caché.", exception.getMessage());
    }

    @Test
    void testCalcularResultado_ConPorcentajeCero() {
        when(porcentajeCache.tieneValorValido()).thenReturn(true);
        when(porcentajeCache.obtenerPorcentaje()).thenReturn(BigDecimal.ZERO);

        BigDecimal resultado = calculadoraService.calcularResultado(
                new BigDecimal("40.555"), new BigDecimal("59.445"));

        assertEquals(new BigDecimal("100.00"), resultado); // 40.555 + 59.445 = 100.00
    }
}
