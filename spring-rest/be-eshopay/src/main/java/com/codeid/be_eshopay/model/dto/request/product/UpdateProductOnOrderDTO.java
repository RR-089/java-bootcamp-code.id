package com.codeid.be_eshopay.model.dto.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductOnOrderDTO {
    @NotNull
    private Long id;

    @NotNull
    @Min(1)
    private Integer quantity;

}
