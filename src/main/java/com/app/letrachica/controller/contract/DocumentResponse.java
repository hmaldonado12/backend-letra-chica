package com.app.letrachica.controller.contract;

import java.time.LocalDateTime;

public class DocumentResponse {
    private String id;
    private String title;
    private String status;
    private String categoryId;
    private LocalDateTime createdAt;
    private String summary;

    public DocumentResponse(String id, String title, String status, String categoryId, LocalDateTime createdAt, String summary) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getSummary() {
        return summary;
    }
}
