package com.sistem.recycle.Services;

import com.sistem.recycle.Collections.MaterialCollection;
import com.sistem.recycle.Mappers.MaterialMapper;
import com.sistem.recycle.Repositories.MaterialRepositories;
import com.sistem.recycle.Request.MaterialRequest;
import com.sistem.recycle.Request.MaterialUpdateRequest;
import com.sistem.recycle.Response.MaterialResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialServicesImpl implements MaterialServicesInterface {

    private final MaterialRepositories materialRepositories;
    private final MaterialMapper materialMapper;

    private String fechaActual() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public MaterialResponse crearMaterial(MaterialRequest request) {
        MaterialResponse response = new MaterialResponse();
        try {
            if (materialRepositories.existsByTipoMaterial(request.getTipoMaterial())) {
                response.setMensaje("Ya existe un material con ese tipo");
                return response;
            }

            MaterialCollection material = materialMapper.toCollection(request);
            material.setEstado("Activo");
            material.setFechaCreacion(fechaActual());

            MaterialCollection guardado = materialRepositories.save(material);
            response = materialMapper.toResponse(guardado);
            response.setMensaje("Material creado con éxito");

        } catch (Exception e) {
            response.setMensaje("Error al crear material: " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<MaterialResponse> listarMateriales() {
        try {
            return materialRepositories.findAll()
                    .stream()
                    .map(materialMapper::toResponse)
                    .peek(m -> m.setMensaje("OK"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            MaterialResponse error = new MaterialResponse();
            error.setMensaje("Error al listar materiales: " + e.getMessage());
            return List.of(error);
        }
    }

    @Override
    public MaterialResponse obtenerMaterialPorTipo(String tipoMaterial) {
        MaterialResponse response = new MaterialResponse();
        try {
            MaterialCollection material = materialRepositories.findByTipoMaterial(tipoMaterial)
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));

            response = materialMapper.toResponse(material);
            response.setMensaje("Material encontrado");

        } catch (Exception e) {
            response.setMensaje(e.getMessage());
        }
        return response;
    }

    @Override
    public MaterialResponse actualizarMaterial(String tipoMaterial, MaterialUpdateRequest request) {
        MaterialResponse response = new MaterialResponse();
        try {
            MaterialCollection material = materialRepositories.findByTipoMaterial(tipoMaterial)
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));

            if (request.getEquivalenciaPuntos() != null)
                material.setEquivalenciaPuntos(request.getEquivalenciaPuntos());

            if (request.getDescripcion() != null)
                material.setDescripcion(request.getDescripcion());

            MaterialCollection actualizado = materialRepositories.save(material);
            response = materialMapper.toResponse(actualizado);
            response.setMensaje("Material actualizado con éxito");

        } catch (Exception e) {
            response.setMensaje("Error al actualizar material: " + e.getMessage());
        }
        return response;
    }

    @Override
    public MaterialResponse eliminarMaterial(String tipoMaterial) {
        MaterialResponse response = new MaterialResponse();
        try {
            MaterialCollection material = materialRepositories.findByTipoMaterial(tipoMaterial)
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));

            if (material.getEstado().equalsIgnoreCase("Inactivo")) {
                response.setMensaje("El material ya está inactivo");
                return response;
            }

            material.setEstado("Inactivo");
            materialRepositories.save(material);

            response.setMensaje("Material inactivado correctamente");
            response.setTipoMaterial(material.getTipoMaterial());

        } catch (Exception e) {
            response.setMensaje("Error al eliminar material: " + e.getMessage());
        }
        return response;
    }

    @Override
    public MaterialResponse activarMaterial(String tipoMaterial) {
        MaterialResponse response = new MaterialResponse();
        try {
            MaterialCollection material = materialRepositories.findByTipoMaterial(tipoMaterial)
                    .orElseThrow(() -> new RuntimeException("Material no encontrado"));

            if (material.getEstado().equalsIgnoreCase("Activo")) {
                response.setMensaje("El material ya está activo");
                return response;
            }

            material.setEstado("Activo");
            materialRepositories.save(material);

            response.setMensaje("Material activado con éxito");

        } catch (Exception e) {
            response.setMensaje(e.getMessage());
        }
        return response;
    }
}
