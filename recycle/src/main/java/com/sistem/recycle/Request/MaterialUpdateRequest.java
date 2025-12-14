package com.sistem.recycle.Request;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class MaterialUpdateRequest {

    private Integer equivalenciaPuntos;

    private String descripcion;
}
