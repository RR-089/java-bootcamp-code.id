package com.codeid.be_eshopay.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items", schema = "oe")
public class CartItem extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "cart_item_seq_gen",
            sequenceName = "oe.cart_items_cart_item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_item_seq_gen"
    )
    @Column(name = "cart_item_id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "discount")
    private Double discount;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;


}
