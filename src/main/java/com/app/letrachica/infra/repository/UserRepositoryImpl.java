package com.app.letrachica.infra.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;
import com.app.letrachica.infra.repository.jpa.UserEntity;
import com.app.letrachica.infra.repository.jpa.UserJpaRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
    
    private final UserJpaRepository jpaRepository;

    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(entity -> new User(entity.getName(), entity.getEmail()));
    }

    @Override
    public void save(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        jpaRepository.save(entity);
    }
}
