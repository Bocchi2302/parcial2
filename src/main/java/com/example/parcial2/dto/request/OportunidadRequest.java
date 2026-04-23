package com.example.parcial2.dto.request;

import com.example.parcial2.entity.EtapaOportunidad;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OportunidadRequest {

    @NotBlank(message = "must not be blank")
    @Size(max = 160, message = "must be at most 160 characters")
    private String titulo;

    @Size(max = 1000, message = "must be at most 1000 characters")
    private String descripcion;

    @NotNull(message = "must not be null")
    @DecimalMin(value = "0.01", message = "must be greater than 0")
    private BigDecimal monto;

    @NotNull(message = "must not be null")
    private EtapaOportunidad etapa;

    @NotNull(message = "must not be null")
    private LocalDate fechaCierreEstimada;

    @NotNull(message = "must not be null")
    private Long creadoPorId;
}
