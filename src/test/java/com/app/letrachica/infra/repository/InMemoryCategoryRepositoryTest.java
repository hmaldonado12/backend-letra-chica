package com.app.letrachica.infra.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.app.letrachica.core.domain.Category;

public class InMemoryCategoryRepositoryTest {

    @Test
    void testSaveAndFindByIdShouldWorkCorrectly() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        Category category = new Category("Finanzas","user-1", "test@example");
        repository.save(category);

        assertTrue(repository.findById(category.getId()).isPresent(), 
                   "Category should be found by ID");
        assertEquals("Finanzas", repository.findById(category.getId()).get().getName(), 
                     "Category name should match the saved category");
    }

    @Test
    void testFindByIdShouldReturnEmptyIfNotFound() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        
        assertTrue(repository.findById("non-existing-id").isEmpty(), 
                   "Should return empty for non-existing category ID");
    }

    @Test
    void testFindByNameShouldWorkCorrectly() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        Category category = new Category("Salud", "user-1", "test@example");
        repository.save(category);

        assertTrue(repository.findByName("Salud").isPresent(), 
                   "Category should be found by name");
        assertEquals("Salud", repository.findByName("Salud").get().getName(), 
                     "Category name should match the saved category");
    }

    @Test
    void testFindByNameShouldReturnEmptyIfNotFound() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        
        assertTrue(repository.findByName("Non-existing").isEmpty(), 
                   "Should return empty for non-existing category name");
    }

    @Test
    void testGetAllShouldWorkCorrectly() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        Category category1 = new Category("Alquiler", "user-1", "test@example");
        Category category2 = new Category("Trabajo", "user-1", "test@example");
        repository.save(category1);
        repository.save(category2);

        assertEquals(2, repository.getAll().size(), 
                     "Should return all saved categories");
        assertTrue(repository.getAll().contains(category1), 
                   "Should contain the first saved category");
        assertTrue(repository.getAll().contains(category2), 
                   "Should contain the second saved category");
    }
}
