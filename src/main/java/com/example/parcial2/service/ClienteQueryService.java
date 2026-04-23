package com.example.parcial2.service;

import com.example.parcial2.dto.response.ClienteResponse;

import java.util.List;

public interface ClienteQueryService {
    ClienteResponse obtenerClientePorId(Long id);
    List<ClienteResponse> listarClientes();
}
