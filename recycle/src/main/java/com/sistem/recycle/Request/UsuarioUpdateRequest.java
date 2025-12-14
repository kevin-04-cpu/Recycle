package com.sistem.recycle.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioUpdateRequest {


    private String nombreCompleto;
    @Email(message = "El correo no tiene un formato válido")
    private String correo;
    private String telefono;
    private String direccion;

}
