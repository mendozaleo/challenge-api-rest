package com.challenge.backend_api_rest.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.challenge.backend_api_rest.shared.dto.RegistroLlamadaDto;

public interface RegistroLlamadaService {

    CompletableFuture<Void> registrarLlamadaAsync(
            LocalDateTime fecha,
            String endpoint,
            String parametros,
            String respuesta,
            String error,
            boolean exitoso);

    List<RegistroLlamadaDto> obtenerHistorialLlamadas();

}
