package com.codeid.be_eshopay.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailKeyDTO {
    @NotBlank
    private Long orderId;

    @NotBlank
    private Long productId;
}
