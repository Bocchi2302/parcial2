package com.example.parcial2.mapper;

import com.example.parcial2.dto.request.ContactoRequest;
import com.example.parcial2.dto.response.ContactoResponse;
import com.example.parcial2.entity.Contacto;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {

    public Contacto toEntity(ContactoRequest request) {
        return Contacto.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .cargo(request.getCargo())
                .build();
    }

    public ContactoResponse toResponse(Contacto contacto) {
        return ContactoResponse.builder()
                .id(contacto.getId())
                .nombre(contacto.getNombre())
                .correo(contacto.getCorreo())
                .telefono(contacto.getTelefono())
                .cargo(contacto.getCargo())
                .build();
    }
}
