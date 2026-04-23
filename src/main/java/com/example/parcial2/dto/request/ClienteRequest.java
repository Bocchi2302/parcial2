package com.example.parcial2.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {

    @NotBlank(message = "must not be blank")
    @Size(max = 120, message = "must be at most 120 characters")
    private String nombre;

    @Email(message = "must be a well-formed email address")
    @NotBlank(message = "must not be blank")
    @Size(max = 120, message = "must be at most 120 characters")
    private String correo;

    @Size(max = 20, message = "must be at most 20 characters")
    private String telefono;

    @Size(max = 255, message = "must be at most 255 characters")
    private String direccion;

    @NotNull(message = "must not be null")
    private Long usuarioAsignadoId;
}
