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
public class CategoryController {

    private final UserRepository userRepository;

    public CategoryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Category> getCategories(@PathVariable("id") String userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getCategories)
                   .orElse(List.of());
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
            .flatMap(user -> user.getCategories().stream()
            .filter(c -> c.equals(categoryId))
            .findFirst())
        .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
