package com.app.letrachica.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.app.letrachica.infra.repository.jpa.UserEntity;

public class User {
    private String id;
    private String name;
    private String email;
    private List<Category> categories;

    public User(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.categories = new ArrayList<>();
    }

    public User(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.categories = entity.getCategories().stream()
                .map(Category::new)
                .toList();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.removeIf(c -> c.equals(category.getId()));
    }
}
