package com.sistem.recycle.Services;

import com.sistem.recycle.Collections.EntregaCollection;
import com.sistem.recycle.Collections.MaquinaCollection;
import com.sistem.recycle.Collections.MaterialCollection;
import com.sistem.recycle.Collections.UsuarioCollection;
import com.sistem.recycle.Mappers.EntregaMapper;
import com.sistem.recycle.Repositories.EntregaRepositories;
import com.sistem.recycle.Repositories.MaquinaRepositories;
import com.sistem.recycle.Repositories.MaterialRepositories;
import com.sistem.recycle.Repositories.UsuarioRepositories;
import com.sistem.recycle.Request.EntregaRequest;
import com.sistem.recycle.Response.EntregaResponse;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntregaServicesImpl implements EntregaServicesInterface {

    private final EntregaRepositories entregaRepositories;
    private final UsuarioRepositories usuarioRepositories;
    private final MaterialRepositories materialRepositories;
    private final MaquinaRepositories maquinaRepositories;
    private final EntregaMapper entregaMapper;

    @Override
    public EntregaResponse crearEntrega(EntregaRequest request) {
        EntregaResponse response = new EntregaResponse();

        try {
            // validar usuario
            UsuarioCollection usuario = usuarioRepositories.findById(request.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // validar material
            MaterialCollection material = materialRepositories.findById(request.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));

            // validar maquina
            MaquinaCollection maquina = maquinaRepositories.findById(request.getMaquinaId())
                    .orElseThrow(() -> new RuntimeException("Maquina no encontrado"));

            // mapear request
            EntregaCollection entrega = entregaMapper.toCollection(request);

            // fecha actual
            entrega.setFechaEntrega(
                    LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            );

            // calcular puntos
            int puntosGenerados = request.getCantidad() * material.getEquivalenciaPuntos();
            entrega.setPuntosGenerados(puntosGenerados);

            // guardar entrega
            EntregaCollection guardada = entregaRepositories.save(entrega);

            // sumar puntos al usuario
            usuario.setPuntosAcumulados(
                    usuario.getPuntosAcumulados() + puntosGenerados
            );
            usuarioRepositories.save(usuario);

            response = entregaMapper.toResponse(guardada);
            response.setMensaje("Entrega registrada correctamente");

        } catch (Exception e) {
            response.setMensaje("Error al registrar entrega: " + e.getMessage());
        }

        return response;
    }

    @Override
    public List<EntregaResponse> listarEntregas() {
        try {
            return entregaRepositories.findAll()
                    .stream()
                    .map(entregaMapper::toResponse)
                    .peek(r -> r.setMensaje("OK"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            EntregaResponse error = new EntregaResponse();
            error.setMensaje("Error al listar entregas: " + e.getMessage());
            return List.of(error);
        }
    }

    @Override
    public List<EntregaResponse> listarEntregasPorDocumentoUsuario(String documento) {
        try {
            UsuarioCollection usuario = usuarioRepositories
                    .findByDocumento(documento)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            return entregaRepositories.findByUsuarioId(usuario.getId())
                    .stream()
                    .map(entregaMapper::toResponse)
                    .peek(r -> r.setMensaje("OK"))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            EntregaResponse error = new EntregaResponse();
            error.setMensaje("Error al listar entregas del usuario: " + e.getMessage());
            return List.of(error);
        }
    }
}
