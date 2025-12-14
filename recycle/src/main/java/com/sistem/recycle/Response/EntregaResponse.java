package com.sistem.recycle.Response;

import lombok.Data;

@Data
public class EntregaResponse {

    private String id;
    private Integer cantidad;
    private String fechaEntrega;
    private Integer puntosGenerados;
    private String usuarioId;
    private String maquinaId;
    private String materialId;
    private String mensaje;
}
