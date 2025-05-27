
--Se propciona los scripts para poblar la tabla registro_llamadas;
--Para realizar la prueba del endpoint GET http://localhost:8087 /api/registro-llamadas
DELETE FROM registro_llamadas;

-- Insertar datos de ejemplo

INSERT INTO registro_llamadas (fecha, endpoint, parametros, respuesta, error, exitoso) 
VALUES ('2024-05-08 10:15:30', '/api/calculadora/suma', '{"num1":100.0,"num2":50.0}', '{"resultado":150.0}', NULL, TRUE);

INSERT INTO registro_llamadas (fecha, endpoint, parametros, respuesta, error, exitoso) 
VALUES ('2024-05-08 10:16:45', '/api/calculadora/resta', '{"num1":100.0,"num2":20.0}', '{"resultado":80.0}', NULL, TRUE);

INSERT INTO registro_llamadas (fecha, endpoint, parametros, respuesta, error, exitoso) 
VALUES ('2024-05-08 10:20:15', '/api/calculadora/multiplicacion', '{"num1":12.5,"num2":4.0}', '{"resultado":50.0}', NULL, TRUE);

INSERT INTO registro_llamadas (fecha, endpoint, parametros, respuesta, error, exitoso) 
VALUES ('2024-05-08 10:22:30', '/api/calculadora/division', '{"num1":100.0,"num2":25.0}', '{"resultado":4.0}', NULL, TRUE);

NSERT INTO registro_llamadas (fecha, endpoint, parametros, respuesta, error, exitoso) 
VALUES ('2024-05-08 11:05:40', '/api/usuarios/buscar', '{"id":1234}', '{"id":1234,"nombre":"Juan Pérez","email":"juan@ejemplo.com"}', NULL, TRUE);