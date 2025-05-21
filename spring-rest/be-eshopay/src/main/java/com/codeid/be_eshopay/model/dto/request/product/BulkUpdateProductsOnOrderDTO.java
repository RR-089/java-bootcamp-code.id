package com.codeid.be_eshopay.model.dto.request.product;

import com.codeid.be_eshopay.constant.UpdateProductOnOrderType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkUpdateProductsOnOrderDTO {
    List<UpdateProductOnOrderDTO> data;
    
    private UpdateProductOnOrderType type;

}
