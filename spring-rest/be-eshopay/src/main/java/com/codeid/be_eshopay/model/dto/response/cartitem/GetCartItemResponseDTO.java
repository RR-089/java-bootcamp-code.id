package com.codeid.be_eshopay.model.dto.response.cartitem;

import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.CartItemSupplierDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetCartItemResponseDTO {
    private CartItemKeyDTO id;

    @NotBlank
    @Size(min = 3, max = 40, message =
            "Item name must be between 3 and 40 characters long")
    private String name;

    @Min(0)
    private Double unitPrice;

    @Min(0)
    private Double discount;

    @Min(0)
    private Double discountedUnitPrice;

    @Min(0)
    private Integer quantity;

    private String thumbnailPicture;

    private CartItemSupplierDTO supplier;
}
