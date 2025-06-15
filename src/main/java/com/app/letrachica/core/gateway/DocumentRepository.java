package com.app.letrachica.core.gateway;

import java.util.List;
import java.util.Optional;

import com.app.letrachica.core.domain.Document;

public interface DocumentRepository {
    Optional<Document> findById(String id);
    Optional<Document> findByTitle(String title);
    List<Document> findAll();
    void save(Document document);
    void deleteById(String id);
    List<Document> findByCategoryId(String categoryId);
}
