package com.codeid.be_eshopay.model.dto.request.cartitem;

import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkDeleteItemsRequestDTO {
    @NotNull
    @NotEmpty
    private List<CartItemKeyDTO> cartItemIds;
}
