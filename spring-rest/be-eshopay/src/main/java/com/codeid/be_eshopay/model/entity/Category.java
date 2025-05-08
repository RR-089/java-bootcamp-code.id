package com.codeid.be_eshopay.model.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "categories", schema = "oe")
public class Category extends AbstractEntity {

    @Id
    @SequenceGenerator(
            name = "category_seq_gen",
            sequenceName = "oe.categories_category_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq_gen"
    )
    @Column(name = "category_id")
    private Long id;

    @Nonnull
    @Column(name = "category_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "picture", columnDefinition = "BYTEA")
    private byte[] picture;
}
