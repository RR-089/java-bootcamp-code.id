package com.codeid.be_eshopay.service;


import com.codeid.be_eshopay.model.entity.Cart;

public interface CartService {
    Cart findCartByUserId(Long userId);

    Cart createCart(Long userId);

}
