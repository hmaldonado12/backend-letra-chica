package com.app.letrachica.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.app.letrachica.core.domain.Category;
import com.app.letrachica.core.domain.Document;
import com.app.letrachica.core.gateway.CategoryRepository;
import com.app.letrachica.infra.repository.jpa.CategoryEntity;
import com.app.letrachica.infra.repository.jpa.CategoryJpaRepository;
import com.app.letrachica.infra.repository.jpa.DocumentJpaRepository;
import com.app.letrachica.infra.repository.jpa.UserEntity;
import com.app.letrachica.infra.repository.jpa.UserJpaRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryRepository;
    private final UserJpaRepository userRepository;
    private final DocumentJpaRepository documentRepository;

    public CategoryRepositoryImpl(
        CategoryJpaRepository categoryRepository,
        UserJpaRepository userRepository,
        DocumentJpaRepository documentRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id)
                .map(entity -> new Category(entity.getName(), entity.getUser().getId(), entity.getEmail()));
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name)
                .map(entity -> new Category(entity.getName(), entity.getUser().getId(), entity.getEmail()));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream()
                .map(entity -> new Category(entity.getName(), entity.getUser().getId(), entity.getEmail()))
                .toList();
    }

    @Override
    public void save(Category category) {
        UserEntity userEntity = userRepository.findById(category.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + category.getUserId()));
        CategoryEntity entity = new CategoryEntity(category, userEntity);
        categoryRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Document> findDocumentsByCategoryId(String categoryId) {
        return documentRepository.findByCategoryId(categoryId).stream()
            .map(Document::new)
            .toList();
    }
}
