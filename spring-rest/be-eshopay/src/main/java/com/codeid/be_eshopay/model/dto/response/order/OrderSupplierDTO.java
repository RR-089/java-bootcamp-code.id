package com.codeid.be_eshopay.model.dto.response.order;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderSupplierDTO {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
