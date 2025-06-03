package com.app.letrachica.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public List<Category> getCategories(@PathVariable String userId, @PathVariable String name) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::getCategories)
                   .orElse(List.of());
    }

    @PostMapping("/{categoryName}")
    public HttpStatus addCategory(@PathVariable String userId, @PathVariable String categoryName) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Category category = new Category(categoryName, userId, user.getEmail());
            user.addCategory(category);
            userRepository.save(user);
            return HttpStatus.CREATED;
        }
        return HttpStatus.NOT_FOUND;
    }

    @DeleteMapping("/{categoryName}")
    public List<Category> removeCategory(@PathVariable String userId, @PathVariable String categoryName) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.getCategories().removeIf(c -> c.getName().equals(categoryName));
            userRepository.save(user);
            return user.getCategories();
        }
        return List.of();
    }
}
