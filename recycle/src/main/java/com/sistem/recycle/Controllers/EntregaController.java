package com.sistem.recycle.Controllers;

import com.sistem.recycle.Request.EntregaRequest;
import com.sistem.recycle.Response.EntregaResponse;
import com.sistem.recycle.Services.EntregaServicesInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
@AllArgsConstructor
public class EntregaController {

    private final EntregaServicesInterface entregaServices;

    @PostMapping
    public EntregaResponse crearEntrega(@Valid @RequestBody EntregaRequest request) {
        return entregaServices.crearEntrega(request);
    }

    @GetMapping
    public ResponseEntity<List<EntregaResponse>> listarEntregas() {
        return ResponseEntity.ok(entregaServices.listarEntregas());
    }

    @GetMapping("/{documento}")
    public ResponseEntity<List<EntregaResponse>> listarPorDocumentoUsuario(
            @PathVariable String documento) {
        return ResponseEntity.ok(
                entregaServices.listarEntregasPorDocumentoUsuario(documento)
        );
    }
}
