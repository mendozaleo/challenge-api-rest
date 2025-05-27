package com.challenge.backend_api_rest.application.impl;

import com.challenge.backend_api_rest.domain.model.RegistroLlamada;
import com.challenge.backend_api_rest.domain.repository.RegistroLlamadaRepository;
import com.challenge.backend_api_rest.shared.dto.RegistroLlamadaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistroLlamadaServiceImplTest {

    private RegistroLlamadaRepository repository;
    private RegistroLlamadaServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(RegistroLlamadaRepository.class);
        service = new RegistroLlamadaServiceImpl(repository);
    }

    @Test
    void testRegistrarLlamadaAsync() throws Exception {
        LocalDateTime fecha = LocalDateTime.now();
        String endpoint = "/api/test";
        String parametros = "{\"key\":\"value\"}";
        String respuesta = "{\"status\":\"ok\"}";
        String error = null;
        boolean exitoso = true;

        CompletableFuture<Void> future = service.registrarLlamadaAsync(fecha, endpoint, parametros, respuesta, error,
                exitoso);

        // Forzar ejecución síncrona para verificar comportamiento
        future.get();

        verify(repository, times(1)).save(argThat(registro -> registro.getFecha().equals(fecha) &&
                registro.getEndpoint().equals(endpoint) &&
                registro.getParametros().equals(parametros) &&
                registro.getRespuesta().equals(respuesta) &&
                registro.getError() == error &&
                registro.getExitoso() == exitoso));
    }

    @Test
    void testObtenerHistorialLlamadas() {
        RegistroLlamada registro = new RegistroLlamada();
        registro.setId(1L);
        registro.setFecha(LocalDateTime.of(2024, 1, 1, 12, 0));
        registro.setEndpoint("/api/test");
        registro.setParametros("params");
        registro.setRespuesta("ok");
        registro.setError(null);
        registro.setExitoso(true);

        when(repository.findAll()).thenReturn(List.of(registro));

        List<RegistroLlamadaDto> result = service.obtenerHistorialLlamadas();

        assertEquals(1, result.size());

        RegistroLlamadaDto dto = result.get(0); // accedemos al primer elemento correctamente
        assertEquals(registro.getId(), dto.getId());
        assertEquals(registro.getFecha(), dto.getFecha());
        assertEquals(registro.getEndpoint(), dto.getEndpoint());
        assertEquals(registro.getParametros(), dto.getParametros());
        assertEquals(registro.getRespuesta(), dto.getRespuesta());
        assertEquals(registro.getError(), dto.getError());
        assertEquals(registro.getExitoso(), dto.getExitoso());
    }

}