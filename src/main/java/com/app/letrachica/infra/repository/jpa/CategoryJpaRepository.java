package com.app.letrachica.infra.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findById(String id);
}
