package com.codeid.be_eshopay.model.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 40, message =
            "Product name must be between 3 and 40 characters long")
    private String name;

    @Min(0)
    private Integer quantityPerUnit;

    @Min(0)
    private Double unitPrice;

    @Min(0)
    private Integer unitsInStock;

    @Min(0)
    private Integer unitsOnOrder;

    @Min(0)
    private Integer reorderLevel;

    @Min(value = 0, message = "Discontinued must be 0 or 1")
    @Max(value = 1, message = "Discontinued must be 0 or 1")
    private Byte discontinued;

    private String thumbnailPicture;

    @NotNull
    private SupplierDTO supplier;

    @NotNull
    private CategoryDTO category;

    @AssertTrue(message = "Supplier 'id' must not be null")
    public boolean isSupplierId() {
        return supplier != null && supplier.getId() != null;
    }

    @AssertTrue(message = "Category 'id' must not be null")
    public boolean isCategoryId() {
        return category != null && category.getId() != null;
    }
}
