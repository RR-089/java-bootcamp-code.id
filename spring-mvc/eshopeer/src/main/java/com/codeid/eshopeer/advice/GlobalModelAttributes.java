package com.codeid.eshopeer.advice;

import com.codeid.eshopeer.model.Category;
import com.codeid.eshopeer.service.CategoryService;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Component
@ControllerAdvice
public class GlobalModelAttributes {
    private final CategoryService categoryService;

    private volatile List<Category> cachedCategories;

    public GlobalModelAttributes(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        if (cachedCategories == null) {
            synchronized (this) {
                if (cachedCategories == null) {
                    cachedCategories = categoryService.findAllCategories();
                }
            }
        }


        model.addAttribute("categories", cachedCategories);
    }

    public synchronized void refreshCache() {
        cachedCategories = categoryService.findAllCategories();
    }

}
