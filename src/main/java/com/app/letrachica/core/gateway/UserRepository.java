package com.app.letrachica.core.gateway;

import java.util.Optional;

import com.app.letrachica.core.domain.User;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    void save(User user);
}
