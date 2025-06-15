package com.app.letrachica.infra.repository.jpa;

import java.time.LocalDateTime;

import com.app.letrachica.core.domain.Document;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    @Lob
    @Column(columnDefinition = "CLOB")
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

    public DocumentEntity() {

    }
}
