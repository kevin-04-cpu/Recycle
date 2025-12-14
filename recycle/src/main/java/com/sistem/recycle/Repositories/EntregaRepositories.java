package com.sistem.recycle.Repositories;

import com.sistem.recycle.Collections.EntregaCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntregaRepositories extends MongoRepository<EntregaCollection, ObjectId> {

    List<EntregaCollection> findByUsuarioId(ObjectId usuarioId);

    List<EntregaCollection> findByMaquinaId(ObjectId maquinaId);
}
