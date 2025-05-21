package com.codeid.be_eshopay.model.dto.response.orderdetail;

import com.codeid.be_eshopay.model.dto.OrderDetailKeyDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetOrderDetailResponseDTO {
    private OrderDetailKeyDTO id;

    
}
