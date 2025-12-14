package com.sistem.recycle.Mappers;

import com.sistem.recycle.Collections.UsuarioCollection;
import com.sistem.recycle.Request.UsuarioRequest;
import com.sistem.recycle.Response.UsuarioResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "puntosAcumulados", constant = "0")
    @Mapping(target = "estado", constant = "Activo")
    @Mapping(target = "fechaRegistro", expression = "java(fechaActual())")
    UsuarioCollection toCollection(UsuarioRequest request);

    UsuarioResponse toResponse(UsuarioCollection collection);

    default String fechaActual() {
        return java.time.LocalDate.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    default String map(org.bson.types.ObjectId value) {
        return value != null ? value.toHexString() : null;
    }
}
