package com.example.parcial2.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactoResponse {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String cargo;
}
