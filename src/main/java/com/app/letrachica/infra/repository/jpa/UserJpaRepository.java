package com.app.letrachica.infra.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {    
    Optional<UserEntity> findByEmail(String email);
}
