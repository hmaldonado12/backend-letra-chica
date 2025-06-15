package com.app.letrachica.infra.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import com.app.letrachica.core.domain.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Setter
    private String name;
    @Setter
    private String email;


    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryEntity> categories = new ArrayList<>();

    public UserEntity(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.categories = user.getCategories().stream()
                .map(category -> new CategoryEntity(category, this))
                .toList();
    }

    public UserEntity(User user, String userId) {
        this.id = userId;
        this.name = user.getName();
        this.email = user.getEmail();
        this.categories = user.getCategories().stream()
                .map(category -> new CategoryEntity(category, this))
                .toList();
    }

    public UserEntity() {}

    public void addCategory(CategoryEntity category) {
        this.categories.add(category);
        category.setUser(this);
    }

    public void removeCategory(CategoryEntity category) {
        this.categories.remove(category);
        category.setUser(null);
    }
}
