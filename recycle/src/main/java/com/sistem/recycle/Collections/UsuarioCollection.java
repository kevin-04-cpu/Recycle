package com.sistem.recycle.Collections;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "usuarios")
public class UsuarioCollection {

    @Id
    private ObjectId id;

    @Field("nombre_completo")
    private String nombreCompleto;

    @Field("documento")
    private String documento;

    @Field("telefono")
    private String telefono;

    @Field("direccion")
    private String direccion;

    @Field("correo")
    private String correo;

    @Field("estado")
    private String estado;

    @Field("puntos_acumulados")
    private Integer puntosAcumulados;

    @Field("fecha_registro")
    private String fechaRegistro;
}
