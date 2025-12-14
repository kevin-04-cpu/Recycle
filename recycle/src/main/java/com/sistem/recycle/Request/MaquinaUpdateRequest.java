package com.sistem.recycle.Request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MaquinaUpdateRequest {

    private String ciudad;
    private String departamento;
    private String direccion;
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Integer capacidad;
}
