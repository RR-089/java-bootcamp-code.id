package com.codeid.be_eshopay.model.dto.response.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductResponseDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40, message =
            "Product name must be between 3 and 40 characters long")
    private String name;

    @Min(0)
    private Double unitPrice;

    @Min(0)
    private Integer unitsInStock;

    private String thumbnailPicture;

}
