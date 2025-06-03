package com.app.letrachica.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;
import com.app.letrachica.infra.repository.InMemoryUserRepository;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


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
    
}
