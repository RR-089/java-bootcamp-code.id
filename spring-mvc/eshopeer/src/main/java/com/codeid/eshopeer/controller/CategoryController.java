package com.codeid.eshopeer.controller;

import com.codeid.eshopeer.advice.GlobalModelAttributes;
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
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final GlobalModelAttributes globalModelAttributes;

    public CategoryController(CategoryService categoryService, GlobalModelAttributes globalModelAttributes) {
        this.categoryService = categoryService;
        this.globalModelAttributes = globalModelAttributes;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("picture");
    }

    @GetMapping
    public String findAllCategories(Model model) {
        return "categories/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
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

            String action = (category.getId() == null) ? "Create Category" : "Update Category";
            model.addAttribute("action", action);

            return "categories/upsert";
        }

        try {
            categoryService.saveCategory(category, picture);
            globalModelAttributes.refreshCache();
        } catch (IOException e) {
            result.rejectValue("picture", "error.category", "Failed to upload picture");
            return "categories/upsert";

        }

        return "redirect:/categories";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.findCategoryById(id);

        model.addAttribute("category", category.get());
        model.addAttribute("action", "Update Category");
        return "categories/upsert";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, RedirectAttributes redirectAttributes,
                                 Model model) {
        categoryService.deleteCategory(id);
        globalModelAttributes.refreshCache();
        redirectAttributes.addFlashAttribute("message", "Category ID:  " + id +
                " has been successfully deleted.");

        return "redirect:/categories";
    }


}
