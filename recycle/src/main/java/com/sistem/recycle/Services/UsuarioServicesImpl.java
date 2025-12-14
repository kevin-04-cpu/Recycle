package com.sistem.recycle.Services;

import com.sistem.recycle.Collections.UsuarioCollection;
import com.sistem.recycle.Mappers.UsuarioMapper;
import com.sistem.recycle.Repositories.UsuarioRepositories;
import com.sistem.recycle.Request.UsuarioRequest;
import com.sistem.recycle.Request.UsuarioUpdateRequest;
import com.sistem.recycle.Response.UsuarioResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UsuarioServicesImpl implements UsuarioServicesInterface {

    private final UsuarioRepositories usuarioRepositories;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        UsuarioResponse response = new UsuarioResponse();
        try {
            if (usuarioRepositories.existsByDocumento(request.getDocumento())) {
                response.setMensaje("Ya existe un usuario con ese documento");
                return response;
            }
            if (usuarioRepositories.existsByCorreo(request.getCorreo())) {
                response.setMensaje("Ya existe un usuario con ese correo");
                return response;
            }

            UsuarioCollection usuario = usuarioMapper.toCollection(request);
            usuario.setEstado("Activo");
            usuario.setPuntosAcumulados(0);
            usuario.setFechaRegistro(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


            UsuarioCollection guardado = usuarioRepositories.save(usuario);

            response = usuarioMapper.toResponse(guardado);
            response.setMensaje("Usuario creado con éxito");
        } catch (Exception e) {
            response.setMensaje("Error al crear usuario: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<UsuarioResponse> listarUsuarios() {
        try {
            return usuarioRepositories.findAll()
                    .stream()
                    .map(usuarioMapper::toResponse)
                    .peek(u -> u.setMensaje("OK"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            UsuarioResponse errorResponse = new UsuarioResponse();
            errorResponse.setMensaje("Error al listar usuarios: " + e.getMessage());
            return List.of(errorResponse);
        }
    }

    @Override
    public UsuarioResponse obtenerUsuarioPorDocumento(String documento) {
        UsuarioResponse response = new UsuarioResponse();
        try {
            UsuarioCollection usuario = usuarioRepositories.findByDocumento(documento)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            response = usuarioMapper.toResponse(usuario);
            response.setMensaje("Usuario encontrado");
        } catch (IllegalArgumentException e) {
            response.setMensaje(e.getMessage());
        } catch (Exception e) {
            response.setMensaje("Error al obtener usuario: " + e.getMessage());
        }
        return response;
    }

    @Override
    public UsuarioResponse actualizarUsuario(String documento, UsuarioUpdateRequest request) {
        UsuarioResponse response = new UsuarioResponse();
        try {
            UsuarioCollection usuario = usuarioRepositories.findByDocumento(documento)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            if (request.getNombreCompleto() != null) usuario.setNombreCompleto(request.getNombreCompleto());
            if (request.getCorreo() != null) usuario.setCorreo(request.getCorreo());
            if (request.getTelefono() != null) usuario.setTelefono(request.getTelefono());
            if (request.getDireccion() != null) usuario.setDireccion(request.getDireccion());

            UsuarioCollection actualizado = usuarioRepositories.save(usuario);
            response = usuarioMapper.toResponse(actualizado);
            response.setMensaje("Usuario actualizado con éxito");
        } catch (IllegalArgumentException e) {
            response.setMensaje(e.getMessage());
        } catch (Exception e) {
            response.setMensaje("Error al actualizar usuario: " + e.getMessage());
        }
        return response;
    }

    @Override
    public UsuarioResponse eliminarUsuario(String documento) {
        UsuarioResponse response = new UsuarioResponse();
        try {
            UsuarioCollection usuario = usuarioRepositories.findByDocumento(documento)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            if(usuario.getEstado().equals("Activo")){
                usuario.setEstado("Inactivo");
                usuarioRepositories.save(usuario);
                response.setMensaje("Usuario eliminado correctamente (estado Inactivo)");
                response.setDocumento(usuario.getDocumento());
                response.setNombreCompleto(usuario.getNombreCompleto());
                return response;
            }
            response.setMensaje("El usuario ya esta inactivo");

        } catch (Exception e) {
            response.setMensaje("Error al eliminar usuario: " + e.getMessage());
        }
        return response;
    }

    @Override
    public UsuarioResponse activarUsuario(String documento) {
        UsuarioResponse response = new UsuarioResponse();
        try {
            UsuarioCollection usuario = usuarioRepositories.findByDocumento(documento)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            if(usuario.getEstado().equalsIgnoreCase("Activo")){
                response.setMensaje("el usuario ya se encuentra activo");
                return response;
            }

            usuario.setEstado("Activo");
            usuarioRepositories.save(usuario);

            response.setNombreCompleto(usuario.getNombreCompleto());
            response.setMensaje("Usuario activado con exito");


        } catch (RuntimeException e) {
            response.setMensaje(e.getMessage());
        }
        return response;
    }
}
