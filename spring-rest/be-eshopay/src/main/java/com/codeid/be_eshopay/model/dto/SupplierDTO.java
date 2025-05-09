package com.codeid.be_eshopay.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40, message = "Company name must be between 3 and 40 " +
            "characters" +
            " long")
    private String companyName;
}
