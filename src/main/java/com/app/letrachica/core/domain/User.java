package com.app.letrachica.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.app.letrachica.infra.repository.jpa.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;
    private String name;
    private String email;
    private List<Category> categories;

    public User(String name, String email) {
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
                .collect(Collectors.toList());
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.removeIf(c -> c.equals(category.getId()));
    }
}
