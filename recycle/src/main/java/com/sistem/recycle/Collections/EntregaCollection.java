package com.sistem.recycle.Collections;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "entregas")
public class EntregaCollection {

    @Id
    private ObjectId id;

    @Field("cantidad")
    private Integer cantidad;

    @Field("fecha_entrega")
    private String fechaEntrega;

    @Field("puntos_generados")
    private Integer puntosGenerados;

    @Field("usuario_id")
    private ObjectId usuarioId;

    @Field("maquina_id")
    private ObjectId maquinaId;

    @Field("material_id")
    private ObjectId materialId;
}
