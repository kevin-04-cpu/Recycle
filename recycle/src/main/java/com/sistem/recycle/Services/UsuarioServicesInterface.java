package com.sistem.recycle.Services;

import com.sistem.recycle.Request.UsuarioRequest;
import com.sistem.recycle.Request.UsuarioUpdateRequest;
import com.sistem.recycle.Response.UsuarioResponse;

import java.util.List;

public interface UsuarioServicesInterface {

    UsuarioResponse crearUsuario(UsuarioRequest request);

    List<UsuarioResponse> listarUsuarios();

    UsuarioResponse obtenerUsuarioPorDocumento(String documento);

    UsuarioResponse actualizarUsuario(String documento, UsuarioUpdateRequest request);

    UsuarioResponse eliminarUsuario(String documento);

    UsuarioResponse activarUsuario(String documento);
}
