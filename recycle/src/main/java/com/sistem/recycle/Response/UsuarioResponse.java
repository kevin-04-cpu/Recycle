package com.sistem.recycle.Response;

import lombok.Data;

@Data
public class UsuarioResponse {

    private String id;
    private String nombreCompleto;
    private String documento;
    private String correo;
    private String estado;
    private Integer puntosAcumulados;
    private String fechaRegistro;
    private String mensaje;
}
