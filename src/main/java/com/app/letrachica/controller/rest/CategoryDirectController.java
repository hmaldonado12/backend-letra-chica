package com.app.letrachica.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.letrachica.core.gateway.CategoryRepository;

@RestController
@RequestMapping("/categories")
public class CategoryDirectController {
    
    private static final Logger logger = LoggerFactory.getLogger(CategoryDirectController.class);
    private final CategoryRepository categoryRepository;

    public CategoryDirectController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        logger.info("🗑️ DELETE /categories/{} - Eliminando categoría directamente", categoryId);
        
        try {
            categoryRepository.deleteById(categoryId);
            logger.info("✅ Categoría eliminada exitosamente: {}", categoryId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("❌ Error eliminando categoría {}: {}", categoryId, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
