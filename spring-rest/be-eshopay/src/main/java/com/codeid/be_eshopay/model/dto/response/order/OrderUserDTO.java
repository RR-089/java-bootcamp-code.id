package com.codeid.be_eshopay.model.dto.response.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderUserDTO {
    private Long id;

    private String name;

    private OrderLocationDTO location;
}
