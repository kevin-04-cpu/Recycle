package com.sistem.recycle.Controllers;

import com.sistem.recycle.Request.MaquinaRequest;
import com.sistem.recycle.Request.MaquinaUpdateRequest;
import com.sistem.recycle.Response.MaquinaResponse;
import com.sistem.recycle.Services.MaquinaServicesInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/maquinas")
public class MaquinaController {

    private final MaquinaServicesInterface maquinaServices;

    @PostMapping
    public MaquinaResponse crearMaquina(@Valid @RequestBody MaquinaRequest request) {
        return maquinaServices.crearMaquina(request);
    }

    @GetMapping
    public List<MaquinaResponse> listarMaquinas() {
        return maquinaServices.listarMaquinas();
    }

    @GetMapping("/{id}")
    public MaquinaResponse obtenerMaquina(@PathVariable String id) {
        return maquinaServices.obtenerMaquinaPorId(id);
    }

    @PutMapping("/{id}")
    public MaquinaResponse actualizarMaquina(@PathVariable String id,
                                             @Valid @RequestBody MaquinaUpdateRequest request) {
        return maquinaServices.actualizarMaquina(id, request);
    }

    @DeleteMapping("/{id}")
    public MaquinaResponse eliminarMaquina(@PathVariable String id) {
        return maquinaServices.eliminarMaquina(id);
    }

    @PatchMapping("/{id}")
    public MaquinaResponse activarMaquina(@PathVariable String id) {
        return maquinaServices.activarMaquina(id);
    }
}
