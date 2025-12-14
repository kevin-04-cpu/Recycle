package com.sistem.recycle.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EntregaRequest {

    @NotNull(message = "La cantidad es obligatoria")
    @Min(1)
    private Integer cantidad;

    @NotBlank(message = "El ID del usuario es obligatorio")
    private String usuarioId;

    @NotBlank(message = "El ID de la máquina es obligatorio")
    private String maquinaId;

    @NotBlank(message = "El ID del material es obligatorio")
    private String materialId;
}
