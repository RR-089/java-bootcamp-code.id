package com.codeid.be_eshopay.model.dto.request.cartitem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDTO {
    @NotBlank
    private Long productId;

    @NotBlank
    @Min(0)
    private Integer quantity;

    @Min(0)
    private Double discount;
}
