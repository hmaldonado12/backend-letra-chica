package com.app.letrachica.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.app.letrachica.infra.repository.jpa.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
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
                .map(User::new);
    }

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            UserEntity entity = new UserEntity(user, user.getId());
            jpaRepository.save(entity);
            return;
        }
        UserEntity entity = new UserEntity(user);
        jpaRepository.save(entity);
        user.setId(entity.getId());
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id)
                .map(User::new);
    }

    @Override
    public List<User> getAllUsers() {
        return jpaRepository.findAll().stream()
                .map(User::new)
                .toList();
    }

    private List<CategoryEntity> mapperCategories(User user, UserEntity userEntity) {
        return user.getCategories().stream()
                .map(category -> new CategoryEntity(category, userEntity))
                .collect(Collectors.toList());

    }
}
