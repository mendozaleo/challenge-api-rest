Challenge Backend API REST en Spring Boot

API REST desarrollada en Spring Boot (Java 21) que implementa un sistema de cálculo con porcentaje dinámico, caché en memoria y registro de historial de llamadas. La aplicación utiliza PostgreSQL para almacenar el historial y está completamente containerizada con Docker.

 Funcionalidades Principales
1. Cálculo con Porcentaje Dinámico

Endpoint: POST /api/calculadora/calcular
Descripción: Recibe dos números (num1 y num2), los suma y aplica un porcentaje adicional obtenido de un servicio externo
Funcionalidad: El porcentaje se obtiene de un mock service con valor fijo

2. Caché del Porcentaje

Duración: 30 minutos en memoria
Lógica: Si el servicio externo falla, usa el último valor almacenado en caché
Fallback: Si no hay valor en caché y el servicio falla, devuelve error

3. Historial de Llamadas

Endpoint: GET /api/registro-llamadas
Descripción: Devuelve el historial completo de llamadas realizadas
Información: Incluye fecha, endpoint, parámetros, respuesta o error
Almacenamiento: Base de datos PostgreSQL (registro asíncrono para no afectar el rendimiento)

Tecnologías Utilizada

Java 21 Spring Boot 3.x Spring Data JPA
PostgreSQL (en Docker)
Docker & Docker Compose
Swagger/OpenAPI para documentación
JUnit & Mockito para testing

📦 Requisitos Previos

Docker y Docker Compose instalados
Java 21 (para desarrollo local)
Maven (para compilación)

Instalación y Ejecución
Opción 1: Ejecutar con Docker (Recomendado)

1.Clonar el repositorio
bashgit clone cd challenge-api-rest
cd challenge-api-rest

2.Compilar el proyecto
bash# Con Maven Wrapper
./mvnw clean package -DskipTests
# O con Maven instalado
mvn clean package -DskipTests

3.Levantar los servicios
bashdocker-compose up --build -d

4.Verificar que está funcionando
bash
docker ps

Opción 2: Con Maven instalado

Verificación de la API

Health Check

bashcurl http://localhost:8087/api/calculadora/health

Documentación Swagger

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de Swagger en:
🔗 http://localhost:8087/swagger-ui/index.html

Características de Swagger UI:

Documentación interactiva de todos los endpoints
Probar endpoints directamente desde el navegador
Esquemas de request/response claramente definidos
Códigos de estado HTTP documentados
Ejemplos de uso para cada endpoint
