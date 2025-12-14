package com.sistem.recycle.Controllers;

import com.sistem.recycle.Request.UsuarioRequest;
import com.sistem.recycle.Request.UsuarioUpdateRequest;
import com.sistem.recycle.Response.UsuarioResponse;
import com.sistem.recycle.Services.UsuarioServicesInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioServicesInterface usuarioServices;

    @PostMapping
    public UsuarioResponse crearUsuario(@Valid @RequestBody UsuarioRequest request) {
      return usuarioServices.crearUsuario(request);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        return ResponseEntity.ok(usuarioServices.listarUsuarios());
    }

    @GetMapping("/{documento}")
    public UsuarioResponse obtenerUsuario(@PathVariable String documento) {
        return usuarioServices.obtenerUsuarioPorDocumento(documento);
    }

    @PutMapping("/{documento}")
    public UsuarioResponse actualizarUsuario(@PathVariable String documento,
                                             @Valid @RequestBody UsuarioUpdateRequest request) {
        return usuarioServices.actualizarUsuario(documento,request);
    }

    @DeleteMapping("/{documento}")
    public UsuarioResponse eliminarUsuario(@PathVariable String documento) {
        return usuarioServices.eliminarUsuario(documento);
    }


    @PatchMapping("/{documento}")
    public UsuarioResponse activarUsuario(@PathVariable String documento) {
        return usuarioServices.activarUsuario(documento);
    }
}
