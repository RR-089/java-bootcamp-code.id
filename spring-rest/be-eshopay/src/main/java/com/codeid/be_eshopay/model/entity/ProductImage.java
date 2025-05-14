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
@Table(name = "product_images", schema = "oe")
public class ProductImage {
    @Id
    @SequenceGenerator(
            name = "product_image_seq_gen",
            sequenceName = "oe.product_images_product_image_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_image_seq_gen"
    )
    @Column(name = "product_image_id")
    private Long id;

    @Embedded
    private FileMetaData fileMetaData;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
