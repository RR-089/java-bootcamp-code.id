package com.codeid.eshopeer.controller;

import com.codeid.eshopeer.model.Category;
import com.codeid.eshopeer.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //TODO: consider use dto
        binder.setDisallowedFields("picture");
    }

    @GetMapping
    public String findAllCategories(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/create";
    }

    @PostMapping
    public String createCategory(@ModelAttribute("category") Category category,
                                 BindingResult result,
                                 @RequestParam("picture") MultipartFile picture,
                                 Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation error: " + error.getDefaultMessage())
            );
            return "categories/create";
        }

        try {
            categoryService.saveCategory(category, picture);
        } catch (IOException e) {
            result.rejectValue("picture", "error.category", "Failed to upload picture");
            return "categories/create";
        }

        return "redirect:/categories";
    }


}
