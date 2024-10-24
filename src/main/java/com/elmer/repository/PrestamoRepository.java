package com.elmer.repository;

import com.elmer.entity.PrestamoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrestamoRepository extends MongoRepository<PrestamoEntity, String> {
    Optional<PrestamoEntity> findByLibroIdAndUsuarioId(String libroId, String usuarioId);
}
