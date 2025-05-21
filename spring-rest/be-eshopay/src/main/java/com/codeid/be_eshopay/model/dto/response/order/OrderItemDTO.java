package com.codeid.be_eshopay.model.dto.response.order;

import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderItemDTO {
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
    private Double finalPrice;

    @Min(0)
    private Double totalPrice;

    @Min(0)
    private Double totalDiscount;

    @Min(0)
    private Integer quantity;

    private String thumbnailPicture;

    private List<String> ItemImages;

}
