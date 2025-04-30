package com.codeid.eshopeer.service;

import com.codeid.eshopeer.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    Category saveCategory(Category category);

    void deleteCategory(Long id);
}
