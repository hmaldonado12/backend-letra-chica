package com.app.letrachica.controller.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.app.letrachica.controller.contract.CreateCategoryRequest;
import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;

public class CategoryControllerTest {

    private UserRepository userRepository;
    private CategoryController categoryController;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        categoryController = new CategoryController(userRepository);
        user = new User("Test User", "test@example.com");
    }

    @AfterEach
    void tearDown() {
        user = null;
        userRepository = null;
        categoryController = null;
    }

    @Test
    void testAddCategoryShouldReturnCreated() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("Finanzas");

        // Act
        HttpStatus status = categoryController.addCategory(user.getId(), request);

        // Assert
        assertEquals(HttpStatus.CREATED, status);
        assertEquals(1, user.getCategories().size());
        assertEquals("Finanzas", user.getCategories().get(0).getName());
        verify(userRepository).save(user);
    }

    @Test
    void testGetCategoriesShouldReturnUserCategories() {
        // Arrange
        user.addCategory(new Category("Alquiler", user.getId(), user.getEmail()));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        List<Category> categories = categoryController.getCategories(user.getId());
        
        // Assert
        assertEquals(1, categories.size());
        assertEquals("Alquiler", categories.get(0).getName());
    }

    @Test
    void testAddCategoryShouldReturnNotFoundIfUserMissing() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());
        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName("Trabajo");

        // Act
        HttpStatus status = categoryController.addCategory(user.getId(), request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, status);
        verify(userRepository, never()).save(any());
    }

    @Test
    void testRemoveCategoryShouldDeleteCategory() {
        // Arrange
        Category category = new Category("Salud", user.getId(), user.getEmail());
        user.addCategory(category);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act
        Category removed = categoryController.removeCategory(user.getId(), category.getId());

        // Assert
        assertTrue(user.getCategories().isEmpty());
        assertTrue(category.equals(removed.getId()));
        verify(userRepository).save(user);
    }

    @Test
    void testRemoveCategoryShouldThrowIfNotFound() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryController.removeCategory(user.getId(), "non-existent-id");
        });
        verify(userRepository, never()).save(any());
    }

    @Test
    void testRemoveCategoryShouldThrowIfUserNotFound() {
        // Arrange
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            categoryController.removeCategory(user.getId(), "any-id");
        });
        verify(userRepository, never()).save(any());
    }
}
