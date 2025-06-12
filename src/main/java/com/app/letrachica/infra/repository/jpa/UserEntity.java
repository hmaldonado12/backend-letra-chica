package com.app.letrachica.infra.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import com.app.letrachica.core.domain.User;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> categories = new ArrayList<>();

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.categories = user.getCategories().stream()
                .map(category -> new CategoryEntity(category, this))
                .toList();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CategoryEntity> getCategories() {
        return this.categories;
    }

    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
        category.setUser(this);
    }

    public void removeCategory(CategoryEntity category) {
        this.categories.remove(category);
        category.setUser(null);
    }
}
