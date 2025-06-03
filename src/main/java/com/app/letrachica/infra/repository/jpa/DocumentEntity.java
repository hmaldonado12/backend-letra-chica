package com.app.letrachica.infra.repository.jpa;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String summary;
    private LocalDateTime createdAt;

    // TODO: Replace with @ManyToOne when UserEntity is ready
    private String userId;

    // TODO: Replace with @ManyToOne when CategoryEntity is ready
    private String categoryId;

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

    public String getUserId() {
        return userId;
    }

    public String getCategoryId() {
        return categoryId;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
