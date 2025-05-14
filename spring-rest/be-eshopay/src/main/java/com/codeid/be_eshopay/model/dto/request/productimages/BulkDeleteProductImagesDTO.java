package com.codeid.be_eshopay.model.dto.request.productimages;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkDeleteProductImagesDTO {
    @NotNull(message = "Data list cannot be null")
    @NotEmpty(message = "Data list cannot be empty")
    List<String> data;
}
