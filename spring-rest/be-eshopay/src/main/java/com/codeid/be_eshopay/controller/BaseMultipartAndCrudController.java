package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

public abstract class BaseMultipartAndCrudController<T, ID> extends BaseCrudController<T, ID> {
    @PostMapping(consumes = {"multipart/form-data"}, value = "/upload")
    public abstract ResponseEntity<ResponseDTO<?>> createMultipart(
            @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description);

    @GetMapping("/view/{fileName:.+}")
    public abstract ResponseEntity<?> viewImage(@PathVariable String fileName);

    public String determineContentType(String fileName) {
        // Implementasi sederhana - bisa diganti dengan cara yang lebih robust
        if (fileName.toLowerCase().endsWith(".png")) {
            return "image/png";
        } else if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.toLowerCase().endsWith(".gif")) {
            return "image/gif";
        }

        return "application/octet-stream";
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public abstract ResponseEntity<ResponseDTO<?>> updateMultipart(
            @PathVariable ID id,
            @RequestPart("data") T dto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description);
}
