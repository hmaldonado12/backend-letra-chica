package com.app.letrachica.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.app.letrachica.infra.repository.jpa.CategoryEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private String id;
    private String name;
    private String email;
    private String userId;
    private List<Document> documents;

    public Category(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.documents = new ArrayList<>();
    }

    public Category(CategoryEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.userId = entity.getUser() != null ? entity.getUser().getId() : null;
        this.documents = entity.getDocuments().stream()
                .map(Document::new)
                .toList();
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }
}
