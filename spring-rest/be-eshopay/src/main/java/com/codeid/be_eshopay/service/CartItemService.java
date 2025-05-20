package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.request.cartitem.BulkCreateItemsRequestDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.BulkDeleteItemsRequestDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.CartItemKeyDTO;
import com.codeid.be_eshopay.model.dto.response.cartitem.GetCartItemResponseDTO;
import com.codeid.be_eshopay.model.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<GetCartItemResponseDTO> findAllCartItems();

    GetCartItemResponseDTO findCartItemById(CartItemKeyDTO id);

    CartItem findCartItemEntityById(CartItemKeyDTO id);

    List<GetCartItemResponseDTO> bulkCreateItems(BulkCreateItemsRequestDTO dto);

    GetCartItemResponseDTO updateCartItemQuantity(CartItemKeyDTO id, Integer quantity);

    void bulkDeleteItems(BulkDeleteItemsRequestDTO dto);
}
