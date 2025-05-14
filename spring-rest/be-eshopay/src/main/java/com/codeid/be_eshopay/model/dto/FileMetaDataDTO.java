package com.codeid.be_eshopay.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileMetaDataDTO {
    private String fileName;
    private Float fileSize;
    private String fileType;
    private String fileUri;
    private Integer index;
}
