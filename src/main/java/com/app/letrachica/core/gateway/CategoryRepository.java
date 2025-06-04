package com.app.letrachica.core.gateway;

import com.app.letrachica.core.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(String id);
    Optional<Category> findByName(String name);
    List<Category> findByAll();
    void save(Category category);
}
