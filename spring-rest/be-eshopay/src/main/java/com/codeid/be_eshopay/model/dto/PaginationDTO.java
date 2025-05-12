package com.codeid.be_eshopay.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO<T> {
    private long totalRecords;

    private T data;
}
