package com.codeid.be_eshopay.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryWithPictureResponseDTO {
    private Long id;

    private String name;

    private String description;

    private String picture;
}
