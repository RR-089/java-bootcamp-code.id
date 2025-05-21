package com.codeid.be_eshopay.model.dto.request.order;

import com.codeid.be_eshopay.constant.PaymentType;
import com.codeid.be_eshopay.model.dto.CartItemKeyDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class CreateOrderDTO {
    private PaymentType paymentType;

    private Long locationId;

    @NotNull
    @NotEmpty
    private List<CartItemKeyDTO> cartItemIds;

    private LocalDate requiredDate;


}
