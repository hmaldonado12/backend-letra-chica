package com.app.letrachica.infra.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, String> {
    Optional<DocumentEntity> findByTitle(String title);
    List<DocumentEntity> findByCategoryId(String categoryId);
}
