package com.challenge.backend_api_rest.infrastructure.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.challenge.backend_api_rest.application.service.RegistroLlamadaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class RegistroLlamadasAspect {

    private final RegistroLlamadaService registroService;
    private final ObjectMapper objectMapper;

    public RegistroLlamadasAspect(RegistroLlamadaService registroService, ObjectMapper objectMapper) {
        this.registroService = registroService;
        this.objectMapper = objectMapper;
    }

    @Around("execution(* com.challenge.backend_api_rest.interfaces.rest.*.*(..)) && !execution(* com.challenge.backend_api_rest.interfaces.rest.RegistroLlamadaController.*(..))")
    public Object registrarLlamada(ProceedingJoinPoint joinPoint) throws Throwable {

        String endpoint = obtenerEndpoint(joinPoint);
        String parametros = obtenerParametros(joinPoint);

        try {

            Object resultado = joinPoint.proceed();
            registroService.registrarLlamadaAsync(
                    LocalDateTime.now(),
                    endpoint,
                    parametros,
                    convertirAJson(resultado),
                    null,
                    true);

            return resultado;
        } catch (Exception e) {
            registroService.registrarLlamadaAsync(
                    LocalDateTime.now(),
                    endpoint,
                    parametros,
                    null,
                    e.getMessage(),
                    false);
            throw e;
        }
    }

    private String obtenerEndpoint(ProceedingJoinPoint joinPoint) {
        String clase = joinPoint.getSignature().getDeclaringTypeName();
        String metodo = joinPoint.getSignature().getName();
        return clase + "." + metodo;
    }

    private String obtenerParametros(ProceedingJoinPoint joinPoint) {

        try {
            Object[] args = joinPoint.getArgs();
            return objectMapper.writeValueAsString(args);
        } catch (Exception e) {
            return "Error al serializar parámetros: " + e.getMessage();
        }

    }

    private String convertirAJson(Object objeto) {
        try {
            return objectMapper.writeValueAsString(objeto);
        } catch (Exception e) {
            return "Error al serializar objeto: " + e.getMessage();
        }
    }
}
