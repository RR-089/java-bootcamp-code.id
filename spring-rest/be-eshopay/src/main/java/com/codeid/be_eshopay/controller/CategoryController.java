package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.model.dto.request.CategoryWithPictureRequestDTO;
import com.codeid.be_eshopay.model.dto.response.CategoryWithPictureResponseDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import com.codeid.be_eshopay.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO<CategoryWithPictureResponseDTO>> createCategoryWithPicture(
            @Valid @ModelAttribute CategoryWithPictureRequestDTO requestDTO
    ) {
        HttpStatus statusCode = HttpStatus.CREATED;

        CategoryWithPictureResponseDTO data =
                categoryService.createCategoryWithPicture(requestDTO);


        ResponseDTO<CategoryWithPictureResponseDTO> response =
                ResponseDTO.<CategoryWithPictureResponseDTO>builder()
                           .status(statusCode.value())
                           .message("Create category successful")
                           .data(data)
                           .build();

        return ResponseEntity.status(statusCode).body(response);

    }
}
