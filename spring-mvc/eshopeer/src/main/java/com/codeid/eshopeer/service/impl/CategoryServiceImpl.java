package com.codeid.eshopeer.service.impl;

import com.codeid.eshopeer.model.Category;
import com.codeid.eshopeer.repository.CategoryRepository;
import com.codeid.eshopeer.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category, MultipartFile picture) throws IOException {
        Category existingCategory = categoryRepository.findById(category.getId()).get();

        if (!picture.isEmpty()) {
            category.setPicture(picture.getBytes());
        } else if (picture.isEmpty() && existingCategory.getPicture() == null) {
            category.setPicture(null);
        } else {
            category.setPicture(existingCategory.getPicture());
        }

        return categoryRepository.save(category);
    }


    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
