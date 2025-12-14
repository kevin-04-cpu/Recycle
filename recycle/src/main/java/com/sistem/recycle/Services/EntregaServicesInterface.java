package com.sistem.recycle.Services;

import com.sistem.recycle.Request.EntregaRequest;
import com.sistem.recycle.Response.EntregaResponse;

import java.util.List;

public interface EntregaServicesInterface {

    EntregaResponse crearEntrega(EntregaRequest request);

    List<EntregaResponse> listarEntregas();

    List<EntregaResponse> listarEntregasPorDocumentoUsuario(String documento);
}
