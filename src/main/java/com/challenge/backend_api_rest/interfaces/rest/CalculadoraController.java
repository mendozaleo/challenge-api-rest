package com.challenge.backend_api_rest.interfaces.rest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.backend_api_rest.application.service.CalculadoraService;
import com.challenge.backend_api_rest.interfaces.rest.dto.ResultadoResponse;
import com.challenge.backend_api_rest.shared.dto.ErrorResponse;
import com.challenge.backend_api_rest.shared.dto.OperacionRequest;
import com.challenge.backend_api_rest.shared.exeption.CalculadoraException;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/calculadora")
@Tag(name = "Calculadora", description = "API para realizar operaciones matemáticas con la calculadora")
public class CalculadoraController {

    @Autowired
    private CalculadoraService calculadoraService;

    @PostMapping("/calcular")
    @Operation(summary = "Realiza una operación matemática con dos números")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación realizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ResultadoResponse.class), examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{ \"resultado\": 165 }"))),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{ \"status\": 400, \"mensaje\": \"Parámetros inválidos\", \"timestamp\": \"2025-05-11T02:23:09.364Z\" }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class), examples = @io.swagger.v3.oas.annotations.media.ExampleObject(value = "{ \"status\": 500, \"mensaje\": \"Error inesperado del servidor\", \"timestamp\": \"2025-05-11T02:23:09.368Z\" }")))
    })
    public ResponseEntity<ResultadoResponse> calcular(

            @RequestBody OperacionRequest request) {

        if (request == null || request.getNum1() == null || request.getNum2() == null) {
            throw new CalculadoraException("Los parámetros num1 y num2 son obligatorios", HttpStatus.BAD_REQUEST);
        }

        try {
            BigDecimal resultado = calculadoraService.calcularResultado(request.getNum1(), request.getNum2());
            return ResponseEntity.ok(new ResultadoResponse(resultado));
        } catch (ArithmeticException e) {
            throw new CalculadoraException("Error aritmético: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new CalculadoraException("Error al procesar la operación: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
