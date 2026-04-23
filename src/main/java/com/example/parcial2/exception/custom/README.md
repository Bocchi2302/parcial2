# Excepciones personalizadas

Este paquete contiene las excepciones de negocio usadas por el CRM.

## Excepciones disponibles
- `ResourceNotFoundException`: se usa cuando un cliente, contacto, oportunidad o usuario no existe.
- `DuplicateResourceException`: se usa cuando se intenta crear un recurso con un identificador o correo duplicado.
- `UnauthorizedAccessException`: se usa cuando el usuario autenticado no tiene permiso para la operación.

## Uso esperado
Las excepciones de este paquete se lanzan desde la capa de servicio y se convierten en respuestas HTTP desde el `@RestControllerAdvice` global.

## Convención
- `404 NOT FOUND` para recursos inexistentes.
- `409 CONFLICT` para duplicados.
- `403 FORBIDDEN` para permisos insuficientes.
