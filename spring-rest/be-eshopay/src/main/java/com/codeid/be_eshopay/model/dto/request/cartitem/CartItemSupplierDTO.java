package com.codeid.be_eshopay.model.dto.request.cartitem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemSupplierDTO {
    private Long id;
    private String name;
}
