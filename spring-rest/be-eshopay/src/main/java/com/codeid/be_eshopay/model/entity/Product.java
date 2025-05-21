package com.codeid.be_eshopay.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "products", schema = "oe")
public class Product extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "product_seq_gen",
            sequenceName = "oe.products_product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_seq_gen"
    )
    @Column(name = "product_id")
    private Long id;

    @Nonnull
    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "quantity_per_unit")
    private Integer quantityPerUnit;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "units_in_stock")
    private Integer unitsInStock;

    @Column(name = "units_on_order")
    private Integer unitsOnOrder;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    @Column(name = "discontinued")
    private Byte discontinued;

    @Column(name = "thumbnail_picture")
    private String thumbnailPicture;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonIgnoreProperties("product")
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages;

    @JsonIgnoreProperties("product")
    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;
}
