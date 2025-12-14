package com.sistem.recycle.Services;

import com.sistem.recycle.Collections.MaquinaCollection;
import com.sistem.recycle.Mappers.MaquinaMapper;
import com.sistem.recycle.Repositories.MaquinaRepositories;
import com.sistem.recycle.Request.MaquinaRequest;
import com.sistem.recycle.Request.MaquinaUpdateRequest;
import com.sistem.recycle.Response.MaquinaResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaquinaServicesImpl implements MaquinaServicesInterface {

    private final MaquinaRepositories maquinaRepositories;
    private final MaquinaMapper maquinaMapper;

    @Override
    public MaquinaResponse crearMaquina(MaquinaRequest request) {
        MaquinaResponse response = new MaquinaResponse();
        try {
            MaquinaCollection maquina = maquinaMapper.toCollection(request);
            maquina.setEstado("Activo");
            maquina.setFechaCreacion(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            MaquinaCollection guardada = maquinaRepositories.save(maquina);

            response = maquinaMapper.toResponse(guardada);
            response.setMensaje("Máquina creada con éxito");
        } catch (Exception e) {
            response.setMensaje("Error al crear máquina: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<MaquinaResponse> listarMaquinas() {
        try {
            return maquinaRepositories.findAll()
                    .stream()
                    .map(maquinaMapper::toResponse)
                    .peek(m -> m.setMensaje("OK"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            MaquinaResponse errorResponse = new MaquinaResponse();
            errorResponse.setMensaje("Error al listar máquinas: " + e.getMessage());
            return List.of(errorResponse);
        }
    }

    @Override
    public MaquinaResponse obtenerMaquinaPorId(String id) {
        MaquinaResponse response = new MaquinaResponse();
        try {
            MaquinaCollection maquina = maquinaRepositories.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"));
            response = maquinaMapper.toResponse(maquina);
            response.setMensaje("Máquina encontrada");
        } catch (IllegalArgumentException e) {
            response.setMensaje(e.getMessage());
        } catch (Exception e) {
            response.setMensaje("Error al obtener máquina: " + e.getMessage());
        }
        return response;
    }

    @Override
    public MaquinaResponse actualizarMaquina(String id, MaquinaUpdateRequest request) {
        MaquinaResponse response = new MaquinaResponse();
        try {
            MaquinaCollection maquina = maquinaRepositories.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"));

            if (request.getCiudad() != null) maquina.setCiudad(request.getCiudad());
            if (request.getDepartamento() != null) maquina.setDepartamento(request.getDepartamento());
            if (request.getDireccion() != null) maquina.setDireccion(request.getDireccion());
            if (request.getCapacidad() != null) maquina.setCapacidad(request.getCapacidad());

            MaquinaCollection actualizado = maquinaRepositories.save(maquina);
            response = maquinaMapper.toResponse(actualizado);
            response.setMensaje("Máquina actualizada con éxito");
        } catch (IllegalArgumentException e) {
            response.setMensaje(e.getMessage());
        } catch (Exception e) {
            response.setMensaje("Error al actualizar máquina: " + e.getMessage());
        }
        return response;
    }

    @Override
    public MaquinaResponse eliminarMaquina(String id) {
        MaquinaResponse response = new MaquinaResponse();
        try {
            MaquinaCollection maquina = maquinaRepositories.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"));

            if ("Activo".equalsIgnoreCase(maquina.getEstado())) {
                maquina.setEstado("Inactivo");
                maquinaRepositories.save(maquina);
                response = maquinaMapper.toResponse(maquina);
                response.setMensaje("Máquina inactivada correctamente");
            } else {
                response.setMensaje("La máquina ya está inactiva");
            }
        } catch (Exception e) {
            response.setMensaje("Error al eliminar máquina: " + e.getMessage());
        }
        return response;
    }

    @Override
    public MaquinaResponse activarMaquina(String id) {
        MaquinaResponse response = new MaquinaResponse();
        try {
            MaquinaCollection maquina = maquinaRepositories.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Máquina no encontrada"));

            if ("Activo".equalsIgnoreCase(maquina.getEstado())) {
                response.setMensaje("La máquina ya se encuentra activa");
                return response;
            }

            maquina.setEstado("Activo");
            maquinaRepositories.save(maquina);

            response = maquinaMapper.toResponse(maquina);
            response.setMensaje("Máquina activada correctamente");
        } catch (Exception e) {
            response.setMensaje("Error al activar máquina: " + e.getMessage());
        }
        return response;
    }
}
