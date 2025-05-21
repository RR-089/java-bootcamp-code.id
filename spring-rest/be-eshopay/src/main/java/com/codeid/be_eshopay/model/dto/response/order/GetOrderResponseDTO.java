package com.codeid.be_eshopay.model.dto.response.order;

import com.codeid.be_eshopay.constant.PaymentType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetOrderResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    private PaymentType paymentType;

    @Min(0)
    private Double finalPrice; //orignal field: totalAmount

    @Min(0)
    private Double totalDiscount;

    @Min(0)
    private Double totalPrice;

    @NotNull
    private OrderSupplierDTO supplier;

    @NotNull
    private OrderUserDTO user;

    @NotNull
    private OrderLocationDTO location;

    @NotBlank
    private OrderShipmentDTO shipment;

    @NotNull
    @NotEmpty
    private List<OrderItemDTO> items;
}
