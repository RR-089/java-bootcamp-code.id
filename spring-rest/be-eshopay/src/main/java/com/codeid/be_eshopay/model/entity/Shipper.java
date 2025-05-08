package com.codeid.be_eshopay.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "shippers", schema = "oe")
public class Shipper extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "shipper_seq_gen",
            sequenceName = "oe.shippers_shipper_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipper_seq_gen")
    @Column(name = "shipper_id")
    private Long id;

    @Nonnull
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "phone")
    private String phone;


}
