package com.sistem.recycle.Repositories;

import com.sistem.recycle.Collections.MaquinaCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface MaquinaRepositories extends MongoRepository<MaquinaCollection, ObjectId> {

    List<MaquinaCollection> findByCiudad(String ciudad);

    List<MaquinaCollection> findByEstado(String estado);

    Optional<MaquinaCollection> findById(String id);
}
