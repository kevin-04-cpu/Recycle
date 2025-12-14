package com.sistem.recycle.Response;

import lombok.Data;

@Data
public class MaterialResponse {

    private String id;
    private String tipoMaterial;
    private Integer equivalenciaPuntos;
    private String descripcion;
    private String estado;
    private String fechaCreacion;
    private String mensaje;
}
