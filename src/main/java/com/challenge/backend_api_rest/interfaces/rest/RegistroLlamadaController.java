package com.challenge.backend_api_rest.interfaces.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.backend_api_rest.application.service.RegistroLlamadaService;
import com.challenge.backend_api_rest.shared.dto.RegistroLlamadaDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api/registro-llamadas")
@Tag(name = "Registro de Llamadas", description = "API para consultar el historial de llamadas realizadas")
public class RegistroLlamadaController {

    private final RegistroLlamadaService registroLlamadaService;

    public RegistroLlamadaController(RegistroLlamadaService registroLlamadaService) {
        this.registroLlamadaService = registroLlamadaService;
    }

    @Operation(
        summary = "Obtener historial de llamadas",
        description = "Devuelve una lista con el historial de llamadas realizadas a la API. "
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Historial obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = RegistroLlamadaDto.class))
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Error al obtener el historial"
        )
    })
    @GetMapping
    public ResponseEntity<List<RegistroLlamadaDto>> obtenerHistorialLlamadas(
        @Parameter(description = "Filtrar por endpoint específico (opcional)", example = "/api/calculadora/calcular")
        @RequestParam(required = false) String endpoint,
        
        @Parameter(description = "Filtrar registros desde una fecha (formato ISO: yyyy-MM-ddTHH:mm:ss, opcional)", 
                  example = "2023-05-01T00:00:00")
        @RequestParam(required = false) String fechaDesde,
        
        @Parameter(description = "Filtrar por código de estado HTTP (opcional)", example = "200")
        @RequestParam(required = false) Integer statusCode,
        
        @Parameter(description = "Número máximo de registros a devolver (opcional, por defecto 100)", example = "50")
        @RequestParam(required = false, defaultValue = "100") Integer limit
    ) {
        List<RegistroLlamadaDto> historial = registroLlamadaService.obtenerHistorialLlamadas();
        return ResponseEntity.ok(historial);
    }
}