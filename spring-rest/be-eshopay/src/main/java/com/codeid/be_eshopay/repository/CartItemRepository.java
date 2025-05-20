package com.codeid.be_eshopay.repository;

import com.codeid.be_eshopay.model.entity.CartItem;
import com.codeid.be_eshopay.model.entity.CartItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, CartItemKey> {
    @Query("""
            select ci from CartItem ci
            join ci.product
            where ci.cart.id = :cartId
            """
    )
    List<CartItem> findAllCartItemsByCartId(@Param("cartId") Long cartId);

}
