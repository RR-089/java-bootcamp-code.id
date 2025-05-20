package com.codeid.be_eshopay.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts", schema = "oe")
public class Cart extends AbstractEntity {
    @Id
    @SequenceGenerator(
            name = "cart_seq_gen",
            sequenceName = "oe.carts_cart_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_seq_gen"
    )
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @JsonIgnoreProperties("cart")
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;
}
