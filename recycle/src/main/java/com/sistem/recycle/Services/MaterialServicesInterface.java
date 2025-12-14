package com.sistem.recycle.Services;

import com.sistem.recycle.Request.MaterialRequest;
import com.sistem.recycle.Request.MaterialUpdateRequest;
import com.sistem.recycle.Response.MaterialResponse;

import java.util.List;

public interface MaterialServicesInterface {

    MaterialResponse crearMaterial(MaterialRequest request);

    List<MaterialResponse> listarMateriales();

    MaterialResponse obtenerMaterialPorTipo(String tipoMaterial);

    MaterialResponse actualizarMaterial(String tipoMaterial, MaterialUpdateRequest request);

    MaterialResponse eliminarMaterial(String tipoMaterial);

    MaterialResponse activarMaterial(String tipoMaterial);
}
