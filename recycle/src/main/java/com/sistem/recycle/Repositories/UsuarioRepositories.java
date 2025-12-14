package com.sistem.recycle.Repositories;

import com.sistem.recycle.Collections.UsuarioCollection;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositories extends MongoRepository<UsuarioCollection, ObjectId> {

    Optional<UsuarioCollection> findByDocumento(String documento);
    Optional<UsuarioCollection> findById(String id);

    boolean existsByDocumento(String documento);
    boolean existsByCorreo(String documento);

}
