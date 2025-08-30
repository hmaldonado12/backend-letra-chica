package com.app.letrachica.infra.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {    
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    List<UserEntity> findByEmailList(String email);
    
    Optional<UserEntity> findByEmail(String email);
    
    // Encuentra el primer usuario por email (maneja duplicados)
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email ORDER BY u.id ASC")
    Optional<UserEntity> findFirstByEmail(@Param("email") String email);
}
