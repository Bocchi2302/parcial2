package com.example.parcial2.service;

import com.example.parcial2.dto.request.OportunidadRequest;
import com.example.parcial2.dto.response.OportunidadResponse;

public interface OportunidadService {
    OportunidadResponse crearOportunidad(Long clienteId, OportunidadRequest request);
}
