package com.example.parcial2.mapper;

import com.example.parcial2.dto.request.ClienteRequest;
import com.example.parcial2.dto.response.ClienteResponse;
import com.example.parcial2.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ContactoMapper contactoMapper;

    public ClienteMapper(ContactoMapper contactoMapper) {
        this.contactoMapper = contactoMapper;
    }

    public Cliente toEntity(ClienteRequest request) {
        return Cliente.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .build();
    }

    public void updateEntity(Cliente cliente, ClienteRequest request) {
        cliente.setNombre(request.getNombre());
        cliente.setCorreo(request.getCorreo());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
    }

    public ClienteResponse toResponse(Cliente cliente) {
        return ClienteResponse.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .correo(cliente.getCorreo())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .usuarioAsignadoId(cliente.getUsuarioAsignado().getId())
                .totalOportunidades(cliente.getOportunidades().size())
                .contactos(cliente.getContactos().stream().map(contactoMapper::toResponse).toList())
                .build();
    }
}
