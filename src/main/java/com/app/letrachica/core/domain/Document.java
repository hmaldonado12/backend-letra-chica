package com.app.letrachica.core.domain;

import java.time.LocalDateTime;

import com.app.letrachica.infra.repository.jpa.DocumentEntity;

public class Document {
    private String id;
    private String title;
    private String summary;
    private String status;
    private String userId;
    private String categoryId;
    private LocalDateTime createdAt;

    public Document(String title, String summary, String userId, String categoryId, String status) {
        this.title = title;
        this.summary = summary;
        this.status = status;
        this.userId = userId;
        this.categoryId = categoryId;
        this.createdAt = LocalDateTime.now();
    }

    public Document(DocumentEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.summary = entity.getSummary();
        this.status = entity.getStatus();
        this.userId = entity.getUser() != null ? entity.getUser().getId() : null;
        this.categoryId = entity.getCategory() != null ? entity.getCategory().getId() : null;
        this.createdAt = entity.getCreatedAt();
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

    public String getStatus() {
        return status;
    }

    public String getUserId() {
        return userId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }
}
