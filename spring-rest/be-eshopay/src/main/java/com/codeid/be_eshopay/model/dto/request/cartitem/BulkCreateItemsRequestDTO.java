package com.codeid.be_eshopay.model.dto.request.cartitem;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkCreateItemsRequestDTO {
    @NotNull
    @NotEmpty
    List<ItemDTO> data;
}
