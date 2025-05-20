package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "locations", schema = "hr")
public class Location {
    @Id
    @SequenceGenerator(
            name = "location_seq_gen",
            sequenceName = "hr.locations_location_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "location_seq_gen"
    )
    @Column(name = "location_id")
    private Long id;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state_province")
    private String stateProvince;

    @Column(name = "country_id", nullable = false)
    private String countryId;

}
