package com.codeid.eshopeer.service;

import com.codeid.eshopeer.model.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAllCategories();

    Optional<Category> findCategoryById(Long id);

    Category saveCategory(Category category, MultipartFile picture) throws IOException;

    void deleteCategory(Long id);
}
