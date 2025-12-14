package com.sistem.recycle.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaquinaRequest {

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotBlank(message = "El departamento es obligatorio")
    private String departamento;

    private String direccion;

    @NotNull(message = "La capacidad es obligatoria")
    private Integer capacidad;


    private String fechaCreacion;
}
