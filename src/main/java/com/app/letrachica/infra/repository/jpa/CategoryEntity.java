package com.app.letrachica.infra.repository.jpa;

import java.util.List;

import com.app.letrachica.core.domain.Category;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentEntity> documents;

    public CategoryEntity(Category category, UserEntity user) {
        this.name = category.getName();
        this.email = category.getEmail();
        this.user = user;
        this.documents = category.getDocuments().stream()
                .map(doc -> new DocumentEntity(doc, user, this))
                .toList();
    }

    public CategoryEntity() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void addDocument(DocumentEntity document) {
        this.documents.add(document);
        document.setCategory(this);
    }

    public void removeDocument(DocumentEntity document) {
        this.documents.removeIf(d -> d.getId().equals(document.getId()));
        document.setCategory(null);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }
}
