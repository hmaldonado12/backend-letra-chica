package com.app.letrachica.core.gateway;

import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.domain.Document;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(String id);
    Optional<Category> findByName(String name);
    List<Category> findAll();
    void save(Category category);
    void deleteById(String id);
    List<Document> findDocumentsByCategoryId(String categoryId);
}
