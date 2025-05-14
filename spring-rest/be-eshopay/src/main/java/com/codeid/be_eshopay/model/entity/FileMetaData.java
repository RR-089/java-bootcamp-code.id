package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileMetaData {

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "file_size", nullable = false)
    private Float fileSize;

    @Column(name = "file_type", nullable = false)
    private String fileType;

    @Column(name = "file_uri", nullable = false)
    private String fileUri;

    @Column(name = "index")
    private Integer index;
}
