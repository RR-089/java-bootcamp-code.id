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
@Table(name = "order_details", schema = "oe")
public class OrderDetail {

    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "discount")
    private Double discount;
}
