package com.codeid.be_eshopay.model.dto.request.cartitem;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemKeyDTO {
    @NotBlank
    private Long cartId;

    @NotBlank
    private Long productId;

}
