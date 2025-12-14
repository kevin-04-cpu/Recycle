package com.sistem.recycle.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MaterialRequest {

    @NotBlank(message = "El tipo de material es obligatorio")
    private String tipoMaterial;

    @NotNull(message = "La equivalencia de puntos es obligatoria")
    private Integer equivalenciaPuntos;

    private String descripcion;

}
