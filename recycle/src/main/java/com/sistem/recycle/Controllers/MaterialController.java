package com.sistem.recycle.Controllers;

import com.sistem.recycle.Request.MaterialRequest;
import com.sistem.recycle.Request.MaterialUpdateRequest;
import com.sistem.recycle.Response.MaterialResponse;
import com.sistem.recycle.Services.MaterialServicesInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/materiales")
public class MaterialController {

    private final MaterialServicesInterface materialServices;

    @PostMapping
    public MaterialResponse crearMaterial(@Valid @RequestBody MaterialRequest request) {
        return materialServices.crearMaterial(request);
    }

    @GetMapping
    public List<MaterialResponse> listarMateriales() {
        return materialServices.listarMateriales();
    }

    @GetMapping("/{tipoMaterial}")
    public MaterialResponse obtenerMaterial(@PathVariable String tipoMaterial) {
        return materialServices.obtenerMaterialPorTipo(tipoMaterial);
    }

    @PutMapping("/{tipoMaterial}")
    public MaterialResponse actualizarMaterial(
            @PathVariable String tipoMaterial,
            @RequestBody MaterialUpdateRequest request) {
        return materialServices.actualizarMaterial(tipoMaterial, request);
    }

    @DeleteMapping("/{tipoMaterial}")
    public MaterialResponse eliminarMaterial(@PathVariable String tipoMaterial) {
        return materialServices.eliminarMaterial(tipoMaterial);
    }

    @PatchMapping("/{tipoMaterial}")
    public MaterialResponse activarMaterial(@PathVariable String tipoMaterial) {
        return materialServices.activarMaterial(tipoMaterial);
    }
}
