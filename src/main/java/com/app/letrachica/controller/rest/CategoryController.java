package com.app.letrachica.controller.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.app.letrachica.controller.contract.CreateCategoryRequest;
import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;

@RestController
@RequestMapping("/users/{id}/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final UserRepository userRepository;

    public CategoryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Category> getCategories(@PathVariable("id") String userId) {
        logger.info("📋 GET /users/{}/categories - Obteniendo categorías para usuario", userId);
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Category> categories = user.get().getCategories();
            logger.info("✅ Categorías encontradas: {} categorías para usuario {}", categories.size(), userId);
            for (Category category : categories) {
                logger.info("   - {} (ID: {})", category.getName(), category.getId());
            }
            return categories;
        }
        logger.warn("⚠️ Usuario no encontrado: {}", userId);
        return List.of(); // Retorna lista vacía si el usuario no existe
    }

    @PostMapping
    public HttpStatus addCategory(@PathVariable("id") String userId, @RequestBody CreateCategoryRequest request) {
        logger.info("📝 POST /users/{}/categories - Creando categoría '{}' para usuario", userId, request.getName());
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            boolean exists = user.getCategories().stream()
                .anyMatch(c -> c.getName().equalsIgnoreCase(request.getName()));
            if (exists) {
                logger.warn("⚠️ Ya existe una categoría con el nombre '{}' para el usuario {}", request.getName(), userId);
                return HttpStatus.CONFLICT;
            }
            Category category = new Category(request.getName(), user.getId(), user.getEmail());
            user.addCategory(category);
            userRepository.save(user);
            logger.info("✅ Categoría creada exitosamente: '{}' (ID: {}) para usuario {}", 
                       category.getName(), category.getId(), userId);
            logger.info("📊 Usuario {} ahora tiene {} categorías total", userId, user.getCategories().size());
            return HttpStatus.CREATED;
        }
        logger.error("❌ Error: Usuario no encontrado al crear categoría: {}", userId);
        return HttpStatus.NOT_FOUND;
    }

    @DeleteMapping("/{categoryId}")
    public Category removeCategory(@PathVariable String userId, @PathVariable String categoryId) {
        logger.info("🗑️ DELETE /users/{}/categories/{} - Eliminando categoría", userId, categoryId);
        return userRepository.findById(userId)
            .map(user -> {
                Category toRemove = user.getCategories().stream()
                    .filter(c -> c.equals(categoryId))
                    .findFirst()
                    .orElseThrow(() -> {
                        logger.error("❌ Categoría no encontrada: {} para usuario {}", categoryId, userId);
                        return new RuntimeException("Category not found");
                    });
                user.removeCategory(toRemove);
                userRepository.save(user);
                logger.info("✅ Categoría eliminada exitosamente: '{}' (ID: {}) para usuario {}", 
                           toRemove.getName(), toRemove.getId(), userId);
                return toRemove;
            })
        .orElseThrow(() -> {
            logger.error("❌ Usuario no encontrado al eliminar categoría: {}", userId);
            return new RuntimeException("Category not found");
        });
    }
}
