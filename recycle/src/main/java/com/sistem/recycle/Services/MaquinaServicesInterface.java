package com.sistem.recycle.Services;

import com.sistem.recycle.Request.MaquinaRequest;
import com.sistem.recycle.Request.MaquinaUpdateRequest;
import com.sistem.recycle.Response.MaquinaResponse;

import java.util.List;

public interface MaquinaServicesInterface {

    MaquinaResponse crearMaquina(MaquinaRequest request);

    List<MaquinaResponse> listarMaquinas();

    MaquinaResponse obtenerMaquinaPorId(String codigo);

    MaquinaResponse actualizarMaquina(String codigo, MaquinaUpdateRequest request);

    MaquinaResponse eliminarMaquina(String codigo);

    MaquinaResponse activarMaquina(String codigo);
}
