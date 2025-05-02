package com.codeid.eshopeer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping
    public String categoriesIsRoot() {
        return "redirect:/categories";
    }
}
