package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import com.codeid.be_eshopay.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController extends BaseCrudController<CategoryDTO, Long> {
    private final CategoryService categoryService;

    @Override
    protected BaseCrudService<CategoryDTO, Long> getService() {
        return categoryService;
    }

    @Override
    protected String getEntityName() {
        return "category";
    }

    @Override
    protected String getEntityPluralName() {
        return "categories";
    }
}
