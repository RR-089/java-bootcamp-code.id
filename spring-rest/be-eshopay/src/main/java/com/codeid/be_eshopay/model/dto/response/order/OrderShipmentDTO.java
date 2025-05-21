package com.codeid.be_eshopay.model.dto.response.order;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShipmentDTO {
    @NotBlank
    private String name;

    @NotBlank
    private Double freight;
}
