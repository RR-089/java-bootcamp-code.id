package com.codeid.eshopeer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shippers", schema = "oe")
public class Shipper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 40, message = "Company name must be between 3 and 15 " +
            "characters long")
    @Column(name = "company_name", nullable = false)
    private String companyName;

    //@Max(value = 24, message = "Phone number max 24 characters long")
    @Size(min = 3, max = 24, message = "Phone number must be between 3 and 24 " +
            "characters long")
    @Column(name = "phone")
    private String phone;
}
