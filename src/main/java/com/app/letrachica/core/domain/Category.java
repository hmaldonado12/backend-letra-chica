package com.app.letrachica.core.domain;

import java.util.ArrayList;
import java.util.List;

import com.app.letrachica.infra.repository.jpa.CategoryEntity;

public class Category {
    private String id;
    private String name;
    private String email;
    private String userId;
    private List<Document> documents;

    public Category(String name, String userId, String email) {
        this.id = java.util.UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
