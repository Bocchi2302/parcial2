package com.example.parcial2.dto.response;

import com.example.parcial2.entity.EtapaOportunidad;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Builder
public class OportunidadResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private BigDecimal monto;
    private EtapaOportunidad etapa;
    private LocalDate fechaCierreEstimada;
    private OffsetDateTime creadoEn;
    private Long clienteId;
    private Long creadoPorId;
}
