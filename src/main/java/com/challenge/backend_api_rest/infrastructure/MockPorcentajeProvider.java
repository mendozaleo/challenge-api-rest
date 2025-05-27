package com.challenge.backend_api_rest.infrastructure;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.challenge.backend_api_rest.domain.PorcentajeProvider;

@Component
public class MockPorcentajeProvider implements PorcentajeProvider {

    @Override
    public BigDecimal obtenerPorcentaje() {
        return new BigDecimal("0.10");
    }

}
