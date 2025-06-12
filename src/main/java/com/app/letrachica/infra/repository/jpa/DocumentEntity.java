package com.app.letrachica.infra.repository.jpa;

import java.time.LocalDateTime;

import com.app.letrachica.core.domain.Document;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String summary;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    public DocumentEntity(Document document, UserEntity user, CategoryEntity category) {
        this.id = document.getId();
        this.title = document.getTitle();
        this.summary = document.getSummary();
        this.status = document.getStatus();
        this.createdAt = document.getCreatedAt();
        this.user = user;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public UserEntity getUser() {
        return user;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
    }
}
