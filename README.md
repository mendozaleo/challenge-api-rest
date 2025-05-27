# challenge-backend-api-rest
Challenge Backend API REST en Spring Boot

Funcionalidades principales
1.​ Cálculo con porcentaje dinámico:
○​ Un endpoint que reciba num1 y num2, los sume y aplique un porcentaje
adicional obtenido de un servicio externo (puede ser un mock con valor
fijo).
2.​ Caché del porcentaje:
○​ El porcentaje obtenido debe almacenarse en memoria durante 30
minutos.
○​ Si el servicio externo falla, se usa el último valor almacenado; si no hay,
se devuelve un error.
3.​ Historial de llamadas:
○​ Un endpoint que devuelva el historial de llamadas (fecha, endpoint,
parámetros, respuesta o error).
○​ El registro debe ser asíncrono para no afectar el rendimiento.