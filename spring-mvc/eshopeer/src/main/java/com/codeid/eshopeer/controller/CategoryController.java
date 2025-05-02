package com.codeid.eshopeer.controller;

import com.codeid.eshopeer.model.Category;
import com.codeid.eshopeer.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("picture");
    }

    @GetMapping
    public String findAllCategories(Model model) {
        commonAttributes(model);
        return "categories/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        commonAttributes(model);

        model.addAttribute("category", new Category());
        model.addAttribute("action", "Create Category");
        return "categories/upsert";
    }

    @PostMapping
    public String createCategory(@Valid @ModelAttribute("category") Category category,
                                 BindingResult result,
                                 @RequestParam("picture") MultipartFile picture,
                                 Model model) {

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error ->
                    System.out.println("Validation error: " + error.getDefaultMessage())
            );
            return "categories/upsert";
        }

        try {
            categoryService.saveCategory(category, picture);
        } catch (IOException e) {
            result.rejectValue("picture", "error.category", "Failed to upload picture");
            return "categories/upsert";

        }

        return "redirect:/categories";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.findCategoryById(id);

        commonAttributes(model);
        model.addAttribute("category", category.get());
        model.addAttribute("action", "Update Category");
        return "categories/upsert";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                 Model model) {
        commonAttributes(model);
        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("message", "Category ID:  " + id +
                " has been delete!");

        return "redirect:/categories";
    }


    private void commonAttributes(Model model) {
        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
    }


}
