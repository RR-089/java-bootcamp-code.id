package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.model.entity.Cart;
import com.codeid.be_eshopay.model.entity.User;
import com.codeid.be_eshopay.repository.CartRepository;
import com.codeid.be_eshopay.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;


    @Override
    public Cart findCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId).orElse(createCart(userId));
    }

    @Override
    public Cart createCart(Long userId) {
        return cartRepository
                .save(Cart.builder()
                          .user(User.builder()
                                    .id(userId)
                                    .build())
                          .build());
    }
}
