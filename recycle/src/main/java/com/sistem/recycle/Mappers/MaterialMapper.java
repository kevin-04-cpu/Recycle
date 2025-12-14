package com.sistem.recycle.Mappers;

import com.sistem.recycle.Collections.MaterialCollection;
import com.sistem.recycle.Request.MaterialRequest;
import com.sistem.recycle.Response.MaterialResponse;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

    MaterialCollection toCollection(MaterialRequest request);

    MaterialResponse toResponse(MaterialCollection collection);

    default String map(ObjectId value) {
        return value != null ? value.toHexString() : null;
    }
}
