package com.sistem.recycle.Collections;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "maquinas")
public class MaquinaCollection {

    @Id
    private ObjectId id;

    @Field("ciudad")
    private String ciudad;

    @Field("departamento")
    private String departamento;

    @Field("direccion")
    private String direccion;

    @Field("capacidad")
    private Integer capacidad;

    @Field("estado")
    private String estado;

    @Field("fecha_creacion")
    private String fechaCreacion;
}
