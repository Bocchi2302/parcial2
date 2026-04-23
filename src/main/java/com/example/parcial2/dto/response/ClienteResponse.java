package com.example.parcial2.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private Long usuarioAsignadoId;
    private int totalOportunidades;
    private List<ContactoResponse> contactos;
}
