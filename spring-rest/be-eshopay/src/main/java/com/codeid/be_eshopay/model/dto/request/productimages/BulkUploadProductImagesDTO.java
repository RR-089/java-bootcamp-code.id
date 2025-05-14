package com.codeid.be_eshopay.model.dto.request.productimages;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class BulkUploadProductImagesDTO {
    @NotNull(message = "Data list cannot be null")
    @NotEmpty(message = "Data list cannot be empty")
    private List<MultipartFile> data;
}
