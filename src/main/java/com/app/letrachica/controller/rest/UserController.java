package com.app.letrachica.controller.rest;

import org.springframework.web.bind.annotation.*;

import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;
import com.app.letrachica.infra.repository.InMemoryUserRepository;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Temporary endpoint to get all users
    @GetMapping
    public List<User> getAllUsers() {
        if (userRepository instanceof InMemoryUserRepository repo) {
            return repo.getAllUsers();
        }
        return List.of();
    }

    // Endpoint: user information by ID
    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable String id) {
        return userRepository.findById(id)
            .map(user -> Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "email", user.getEmail(),
                "categories", user.getCategories()
            ))
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
