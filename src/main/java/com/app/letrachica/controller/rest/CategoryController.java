package com.app.letrachica.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.app.letrachica.controller.contract.CreateCategoryRequest;
import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.domain.User;
import com.app.letrachica.core.gateway.UserRepository;

@RestController
@RequestMapping("/users/{id}/categories")
@CrossOrigin(origins = "http://localhost:8080")
public class CategoryController {

    private final UserRepository userRepository;

    public CategoryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Category> getCategories(@PathVariable("id") String userId) {
        Optional<User> user = userRepository.findById(userId);
        List<Category> categoriasPrueba2 = List.of(
                new Category("Alquiler", userId, "test@example.com"),
                new Category("Personal", userId, "test@example.com"),
                new Category("Seguro", userId, "test@example.com"),
                new Category("Otros", userId, "test@example.com")
        );
        if (user.isEmpty()) {
            User testUser = new User(userId, "test@example.com");
            List<Category> categoriasPrueba = List.of(
                    new Category("Alquiler", userId, "test@example.com"),
                    new Category("Personal", userId, "test@example.com"),
                    new Category("Seguro", userId, "test@example.com"),
                    new Category("Otros", userId, "test@example.com")
            );
            categoriasPrueba.forEach(testUser::addCategory);
            user = Optional.of(testUser);
        }
        return categoriasPrueba2;
    }

    @PostMapping
    public HttpStatus addCategory(@PathVariable("id") String userId, @RequestBody CreateCategoryRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Category category = new Category(request.getName(), userId, user.getEmail());
            user.addCategory(category);
            userRepository.save(user);
            return HttpStatus.CREATED;
        }
        return HttpStatus.NOT_FOUND;
    }

    @DeleteMapping("/{categoryId}")
    public Category removeCategory(@PathVariable String userId, @PathVariable String categoryId) {
        return userRepository.findById(userId)
            .map(user -> {
                Category toRemove = user.getCategories().stream()
                    .filter(c -> c.equals(categoryId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Category not found"));
                user.removeCategory(toRemove);
                userRepository.save(user);
                return toRemove;
            })
        .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
