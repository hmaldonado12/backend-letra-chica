package com.app.letrachica.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.app.letrachica.core.domain.Document;
import com.app.letrachica.core.gateway.DocumentRepository;
import com.app.letrachica.infra.repository.jpa.CategoryEntity;
import com.app.letrachica.infra.repository.jpa.CategoryJpaRepository;
import com.app.letrachica.infra.repository.jpa.DocumentEntity;
import com.app.letrachica.infra.repository.jpa.DocumentJpaRepository;
import com.app.letrachica.infra.repository.jpa.UserEntity;
import com.app.letrachica.infra.repository.jpa.UserJpaRepository;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private final DocumentJpaRepository documentRepository;
    private final UserJpaRepository userRepository;
    private final CategoryJpaRepository categoryRepository;

    public DocumentRepositoryImpl(
        DocumentJpaRepository documentRepository,
        UserJpaRepository userRepository,
        CategoryJpaRepository categoryRepository
    ) {
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Document> findById(String id) {
        return documentRepository.findById(id)
                .map(Document::new);
    }

    @Override
    public Optional<Document> findByTitle(String title) {
        return documentRepository.findByTitle(title)
                .map(Document::new);
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAll().stream()
                .map(Document::new).toList();
    }

    @Override
    public void save(Document document) {
        UserEntity userEntity = userRepository.findById(document.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + document.getUserId()));
        CategoryEntity categoryEntity = categoryRepository.findById(document.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + document.getCategoryId()));
        
        DocumentEntity entity = new DocumentEntity(document, userEntity, categoryEntity);
        documentRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        if (!documentRepository.findById(id).isPresent()) {
            throw new RuntimeException("Document not found with id: " + id);
        }
        documentRepository.deleteById(id);
    }

    @Override
    public List<Document> findByCategoryId(String categoryId) {
        return documentRepository.findByCategoryId(categoryId).stream()
            .map(Document::new).toList();
    }
}
