package com.codeid.be_eshopay.model.dto.response.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLocationDTO {
    private Long id;

    private String streetAddress;

    private String stateProvince;

    private String city;

    private String postalCode;

    private String countryId;
}
