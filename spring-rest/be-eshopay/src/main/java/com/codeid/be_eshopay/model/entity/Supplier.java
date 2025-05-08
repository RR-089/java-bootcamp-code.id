package com.codeid.be_eshopay.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "suppliers", schema = "oe")
public class Supplier extends AbstractEntity {

    @Id
    @SequenceGenerator(
            name = "supplier_seq_gen",
            sequenceName = "oe.suppliers_supplier_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "supplier_seq_gen"
    )
    @Column(name = "supplier_id")
    private Long id;

    @Nonnull
    @Column(name = "company_name", nullable = false)
    private String companyName;
}
