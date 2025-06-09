package com.app.letrachica.infra.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;

// @Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(users.get(id));
    }
    
    @Override
    public void save(User user) {
        users.put(user.getId(), user);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
