package com.challenge.backend_api_rest.application.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.challenge.backend_api_rest.application.service.RegistroLlamadaService;
import com.challenge.backend_api_rest.domain.model.RegistroLlamada;
import com.challenge.backend_api_rest.domain.repository.RegistroLlamadaRepository;
import com.challenge.backend_api_rest.shared.dto.RegistroLlamadaDto;

@Service
public class RegistroLlamadaServiceImpl implements RegistroLlamadaService {

    private final RegistroLlamadaRepository repository;

    public RegistroLlamadaServiceImpl(RegistroLlamadaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Async
    public CompletableFuture<Void> registrarLlamadaAsync(
            LocalDateTime fecha,
            String endpoint,
            String parametros,
            String respuesta,
            String error,
            boolean exitoso) {

        RegistroLlamada registro = new RegistroLlamada();
        registro.setFecha(fecha);
        registro.setEndpoint(endpoint);
        registro.setParametros(parametros);
        registro.setRespuesta(respuesta);
        registro.setError(error);
        registro.setExitoso(exitoso);

        repository.save(registro);

        return CompletableFuture.completedFuture(null);
    }

    @Override
    public List<RegistroLlamadaDto> obtenerHistorialLlamadas() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private RegistroLlamadaDto mapToDto(RegistroLlamada registro) {

        return new RegistroLlamadaDto(
                registro.getId(),
                registro.getFecha(),
                registro.getEndpoint(),
                registro.getParametros(),
                registro.getRespuesta(),
                registro.getError(),
                registro.getExitoso());
    }
}
