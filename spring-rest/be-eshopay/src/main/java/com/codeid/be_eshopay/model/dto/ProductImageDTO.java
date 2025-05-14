package com.codeid.be_eshopay.model.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Delegate;

@Data
@Builder
public class ProductImageDTO {
    private Long id;

    @Delegate
    @JsonUnwrapped
    private FileMetaDataDTO fileMetaData;

    private Long productId;
}
