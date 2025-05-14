package com.codeid.be_eshopay.model.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImageDTO {
    private Long id;

    @JsonUnwrapped
    private FileMetaDataDTO fileMetaData;

    private Long productId;
}
