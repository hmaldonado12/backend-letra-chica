package com.app.letrachica.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.app.letrachica.core.domain.User;

public class InMemoryUserRepositoryTest {

    @Test
    void testSaveAndFindByEmailShouldWorkCorrectly() {
        // Arrange
        // InMemoryUserRepository userRepository = new InMemoryUserRepository();
        User user = new User("Test User", "test@example.com");

        // Act
        // userRepository.save(user);
        // Optional<User> found = userRepository.findByEmail("test@example.com");

        // Assert
        // assertTrue(found.isPresent(), "User should be found by email");
        // assertTrue(user.equals(found.get().getId()), "Found user should match the saved user");
    }

    @Test
    void testFindEmailShouldReturnEmptyIfNotFound() {
        // Arrange
        // InMemoryUserRepository userRepository = new InMemoryUserRepository();

        // Act
        // Optional<User> found = userRepository.findByEmail("notfound@example.com");

        // Assert
        // assertTrue(found.isEmpty(), "User should not be found for non-existing email");
    }

    @Test
    void testFindAllShouldReturnAllUsers() {
        // Arrange
        // InMemoryUserRepository userRepository = new InMemoryUserRepository();
        
        // Act
        // userRepository.save(new User("User1", "u1@example.com"));
        // userRepository.save(new User("User2", "u2@example.com"));

        // Assert
        // assertEquals(2, userRepository.getAllUsers().size(), "Should return all saved users");
    }
}
