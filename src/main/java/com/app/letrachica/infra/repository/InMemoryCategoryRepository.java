package com.app.letrachica.infra.repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.gateway.CategoryRepository;

@Repository
public class InMemoryCategoryRepository implements CategoryRepository {
    private final Map<String, Category> categories = new HashMap<>();

    @Override
    public Optional<Category> findById(String id) {
        return Optional.ofNullable(categories.get(id));
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categories.values().stream()
                .filter(category -> category.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Category> getAll() {
        return new ArrayList<>(categories.values());
    }

    @Override
    public void save(Category category) {
        categories.put(category.getId(), category);
    }
}
