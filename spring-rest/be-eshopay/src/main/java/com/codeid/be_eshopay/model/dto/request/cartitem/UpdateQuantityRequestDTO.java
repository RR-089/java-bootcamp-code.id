package com.codeid.be_eshopay.model.dto.request.cartitem;

import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateQuantityRequestDTO {
    @NotNull
    private CartItemKeyDTO cartItemId;

    @Min(0)
    private Integer quantity;
}
