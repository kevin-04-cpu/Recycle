package com.sistem.recycle.Mappers;

import com.sistem.recycle.Collections.MaquinaCollection;
import com.sistem.recycle.Request.MaquinaRequest;
import com.sistem.recycle.Response.MaquinaResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaquinaMapper {

    MaquinaCollection toCollection(MaquinaRequest request);

    MaquinaResponse toResponse(MaquinaCollection collection);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }
}
