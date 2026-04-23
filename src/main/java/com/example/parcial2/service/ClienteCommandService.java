package com.example.parcial2.service;

import com.example.parcial2.dto.request.ClienteRequest;
import com.example.parcial2.dto.request.ContactoRequest;
import com.example.parcial2.dto.response.ClienteResponse;
import com.example.parcial2.dto.response.ContactoResponse;

public interface ClienteCommandService {
    ClienteResponse crearCliente(ClienteRequest request);
    ClienteResponse actualizarCliente(Long id, ClienteRequest request);
    ContactoResponse asociarContacto(Long clienteId, ContactoRequest request);
    void eliminarCliente(Long id);
}
