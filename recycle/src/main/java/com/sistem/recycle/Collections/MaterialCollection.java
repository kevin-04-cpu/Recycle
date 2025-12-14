package com.sistem.recycle.Collections;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "materiales")
public class MaterialCollection {

    @Id
    private ObjectId id;

    @Field("tipo_material")
    private String tipoMaterial;

    @Field("equivalencia_puntos")
    private Integer equivalenciaPuntos;

    @Field("descripcion")
    private String descripcion;

    @Field("estado")
    private String estado;

    @Field("fecha_creacion")
    private String fechaCreacion;
}
