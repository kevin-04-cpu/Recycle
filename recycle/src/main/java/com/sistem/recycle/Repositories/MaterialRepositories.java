package com.sistem.recycle.Repositories;

import com.sistem.recycle.Collections.MaterialCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MaterialRepositories extends MongoRepository<MaterialCollection, ObjectId> {

    Optional<MaterialCollection> findByTipoMaterial(String tipoMaterial);

    boolean existsByTipoMaterial(String tipoMaterial);

    Optional<MaterialCollection> findById(String id);
}
