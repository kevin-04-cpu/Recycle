package com.sistem.recycle.Response;

import lombok.Data;

@Data
public class MaquinaResponse {

    private String id;
    private String ciudad;
    private String departamento;
    private String direccion;
    private Integer capacidad;
    private String estado;
    private String fechaCreacion;
    private String mensaje;
}
