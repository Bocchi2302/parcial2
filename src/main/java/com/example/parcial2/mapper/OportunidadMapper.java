package com.example.parcial2.mapper;

import com.example.parcial2.dto.request.OportunidadRequest;
import com.example.parcial2.dto.response.OportunidadResponse;
import com.example.parcial2.entity.Oportunidad;
import org.springframework.stereotype.Component;

@Component
public class OportunidadMapper {

    public Oportunidad toEntity(OportunidadRequest request) {
        return Oportunidad.builder()
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .monto(request.getMonto())
                .etapa(request.getEtapa())
                .fechaCierreEstimada(request.getFechaCierreEstimada())
                .build();
    }

    public OportunidadResponse toResponse(Oportunidad oportunidad) {
        return OportunidadResponse.builder()
                .id(oportunidad.getId())
                .titulo(oportunidad.getTitulo())
                .descripcion(oportunidad.getDescripcion())
                .monto(oportunidad.getMonto())
                .etapa(oportunidad.getEtapa())
                .fechaCierreEstimada(oportunidad.getFechaCierreEstimada())
                .creadoEn(oportunidad.getCreadoEn())
                .clienteId(oportunidad.getCliente().getId())
                .creadoPorId(oportunidad.getCreadoPor().getId())
                .build();
    }
}
