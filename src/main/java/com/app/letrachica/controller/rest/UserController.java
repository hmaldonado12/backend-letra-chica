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

    // TODO: Implement actual user registration logic
    // Mock endpoint: user authenticated information
    @GetMapping("/me")
    public Map<String, Object> getCurrentUserInfo() {
        // Mock data, replace with actual user information retrieval logic
        return Map.of(
            "id", "user-12345",
            "name", "Test User",
            "email", "test@example.com",
            "categories", List.of("Finance", "Health", "Education")
        );
    }
    
    // TODO: Implement actual user retrieval logic
    // Mock endpoint: user information by ID
    @GetMapping("/{id}")
    public Map<String, Object> getUserById(@PathVariable String id) {
        // Mock data, replace with actual user retrieval logic
        return Map.of(
            "id", id,
            "name", "Test User",
            "email", "testqexample.com",
            "categories", List.of("Finance", "Health", "Education")
        );
    }
}
