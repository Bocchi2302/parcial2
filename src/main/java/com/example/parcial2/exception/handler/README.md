# Manejo de excepciones

El `@RestControllerAdvice` global convierte errores de negocio y validación en respuestas REST homogéneas.

## Tipos de errores que maneja
- Recurso no encontrado: `404 NOT FOUND`
- Duplicado: `409 CONFLICT`
- Acceso denegado: `403 FORBIDDEN`
- Validación de campos: `400 BAD REQUEST`
- Error de autenticación: `401 UNAUTHORIZED`

## Formato de respuesta
```json
{
  "status": "error",
  "code": 403,
  "message": "No tiene permisos para esta operación",
  "errors": ["Access denied"],
  "timestamp": "2026-04-20T12:00:00Z",
  "path": "/clientes/1"
}
```

## Flujo recomendado
1. La capa de servicio valida reglas de negocio y lanza una excepción personalizada si corresponde.
2. Spring valida los DTOs con `@Valid` y anota errores de campo cuando falta un dato requerido.
3. El advice global transforma todo en un error consistente para el cliente HTTP.
