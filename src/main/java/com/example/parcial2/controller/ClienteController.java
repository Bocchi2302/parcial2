package com.example.parcial2.controller;

import com.example.parcial2.config.ApiResponseBuilder;
import com.example.parcial2.dto.request.ClienteRequest;
import com.example.parcial2.dto.request.ContactoRequest;
import com.example.parcial2.dto.request.OportunidadRequest;
import com.example.parcial2.dto.response.ApiResponse;
import com.example.parcial2.dto.response.ClienteResponse;
import com.example.parcial2.dto.response.ContactoResponse;
import com.example.parcial2.dto.response.OportunidadResponse;
import com.example.parcial2.service.ClienteCommandService;
import com.example.parcial2.service.ClienteQueryService;
import com.example.parcial2.service.OportunidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteCommandService clienteCommandService;
    private final ClienteQueryService clienteQueryService;
    private final OportunidadService oportunidadService;
    private final ApiResponseBuilder responseBuilder;

    public ClienteController(ClienteCommandService clienteCommandService,
                             ClienteQueryService clienteQueryService,
                             OportunidadService oportunidadService,
                             ApiResponseBuilder responseBuilder) {
        this.clienteCommandService = clienteCommandService;
        this.clienteQueryService = clienteQueryService;
        this.oportunidadService = oportunidadService;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> create(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse data = clienteCommandService.crearCliente(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Cliente creado correctamente", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> getById(@PathVariable Long id) {
        ClienteResponse data = clienteQueryService.obtenerClientePorId(id);
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente obtenido correctamente", data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> getAll() {
        List<ClienteResponse> data = clienteQueryService.listarClientes();
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Clientes obtenidos correctamente", data));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> update(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        ClienteResponse data = clienteCommandService.actualizarCliente(id, request);
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente actualizado correctamente", data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        clienteCommandService.eliminarCliente(id);
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente eliminado correctamente", null));
    }

    @PostMapping("/{id}/contactos")
    public ResponseEntity<ApiResponse<ContactoResponse>> addContacto(@PathVariable Long id,
                                                                     @Valid @RequestBody ContactoRequest request) {
        ContactoResponse data = clienteCommandService.asociarContacto(id, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Contacto asociado correctamente", data));
    }

    @PostMapping("/{id}/oportunidades")
    public ResponseEntity<ApiResponse<OportunidadResponse>> addOportunidad(@PathVariable Long id,
                                                                            @Valid @RequestBody OportunidadRequest request) {
        OportunidadResponse data = oportunidadService.crearOportunidad(id, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Oportunidad creada correctamente", data));
    }
}
