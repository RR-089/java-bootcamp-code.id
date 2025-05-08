package com.codeid.be_eshopay.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO {
    private Long id;

    @Size(min = 3, max = 15, message = "Category name must be between 3 and 15 " +
            "characters" +
            " long")
    private String name;

    private String description;
}
