package com.sistem.recycle.Mappers;

import com.sistem.recycle.Collections.EntregaCollection;
import com.sistem.recycle.Request.EntregaRequest;
import com.sistem.recycle.Response.EntregaResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EntregaMapper {

    // ========================
    // REQUEST → COLLECTION
    // ========================

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaEntrega", ignore = true)
    @Mapping(target = "puntosGenerados", ignore = true)

    @Mapping(
            target = "usuarioId",
            source = "usuarioId",
            qualifiedByName = "stringToObjectId"
    )
    @Mapping(
            target = "maquinaId",
            source = "maquinaId",
            qualifiedByName = "stringToObjectId"
    )
    @Mapping(
            target = "materialId",
            source = "materialId",
            qualifiedByName = "stringToObjectId"
    )
    EntregaCollection toCollection(EntregaRequest request);

    // ========================
    // COLLECTION → RESPONSE
    // ========================

    @Mapping(target = "mensaje", ignore = true)
    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    @Mapping(target = "usuarioId", source = "usuarioId", qualifiedByName = "objectIdToString")
    @Mapping(target = "maquinaId", source = "maquinaId", qualifiedByName = "objectIdToString")
    @Mapping(target = "materialId", source = "materialId", qualifiedByName = "objectIdToString")
    EntregaResponse toResponse(EntregaCollection collection);

    // ========================
    // CONVERSORES
    // ========================

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        return id == null ? null : new ObjectId(id);
    }

    @Named("objectIdToString")
    default String objectIdToString(ObjectId id) {
        return id == null ? null : id.toHexString();
    }
}
