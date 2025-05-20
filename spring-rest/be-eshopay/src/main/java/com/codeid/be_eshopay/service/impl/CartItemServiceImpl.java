package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.InternalServerException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.request.cartitem.*;
import com.codeid.be_eshopay.model.dto.response.cartitem.GetCartItemResponseDTO;
import com.codeid.be_eshopay.model.entity.Cart;
import com.codeid.be_eshopay.model.entity.CartItem;
import com.codeid.be_eshopay.model.entity.CartItemKey;
import com.codeid.be_eshopay.model.entity.Product;
import com.codeid.be_eshopay.repository.CartItemRepository;
import com.codeid.be_eshopay.service.CartItemService;
import com.codeid.be_eshopay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    private CartItemKeyDTO mapCartItemKeyToDto(CartItemKey entity) {
        return CartItemKeyDTO.builder()
                             .cartId(entity.getCartId())
                             .productId(entity.getProductId())
                             .build();
    }

    private CartItemKey mapCartItemKeyToEntity(CartItemKeyDTO dto) {
        return CartItemKey.builder()
                          .cartId(dto.getCartId())
                          .productId(dto.getProductId())
                          .build();
    }

    private GetCartItemResponseDTO mapToCartItemResponseDto(CartItem entity) {
        return GetCartItemResponseDTO
                .builder()
                .id(this.mapCartItemKeyToDto(entity.getId()))
                .name(entity.getProduct().getName())
                .thumbnailPicture(entity.getProduct().getThumbnailPicture())
                .quantity(entity.getQuantity())
                .unitPrice(entity.getUnitPrice())
                .discount(entity.getDiscount())
                .discountedUnitPrice(
                        entity.getDiscount() > 0 && entity.getDiscount() != null ?
                                entity.getUnitPrice() * (1 - entity.getDiscount())
                                : entity.getUnitPrice())
                .supplier(
                        CartItemSupplierDTO
                                .builder()
                                .id(entity.getProduct().getSupplier().getId())
                                .name(entity.getProduct().getSupplier().getCompanyName())
                                .build()
                )
                .build();
    }

    @Override
    public List<GetCartItemResponseDTO> findAllCartItems() {

        //TODO: Delete this later
        Long hardCodeCartId = 1L;

        return cartItemRepository
                .findAllCartItemsByCartId(hardCodeCartId)
                .stream()
                .map(this::mapToCartItemResponseDto)
                .toList();
    }

    @Override
    public GetCartItemResponseDTO findCartItemById(CartItemKeyDTO id) {
        return cartItemRepository.findById(CartItemKey.builder()

                                                      .build())
                                 .map(this::mapToCartItemResponseDto)
                                 .orElseThrow(() -> new NotFoundException("Cart item with id: " + id +
                                         " not found", null));
    }

    @Override
    public CartItem findCartItemEntityById(CartItemKeyDTO id) {
        return cartItemRepository
                .findById(this.mapCartItemKeyToEntity(id))
                .orElseThrow(() -> new NotFoundException("Cart item with id: " + id +
                        " not found", null));
    }

    @Override
    @Transactional
    public List<GetCartItemResponseDTO> bulkCreateItems(BulkCreateItemsRequestDTO dto) {
        List<Long> productIds = dto.getData()
                                   .stream()
                                   .map(ItemDTO::getProductId)
                                   .toList();

        List<Product> foundProducts =
                productService.findProductsByIds(productIds);

        List<CartItem> cartItems = new ArrayList<>();

        for (Product product : foundProducts) {
            Long productId = product.getId();
            Integer quantity = dto.getData()
                                  .stream()
                                  .filter(itemDTO -> productId.equals(itemDTO.getProductId()))
                                  .map(ItemDTO::getQuantity)
                                  .findFirst()
                                  .orElseThrow(() ->
                                          new InternalServerException("Cannot get " +
                                                  "quantity", null)
                                  );

            Double discount = dto.getData()
                                 .stream()
                                 .filter(itemDTO -> productId.equals(itemDTO.getProductId()))
                                 .map(ItemDTO::getDiscount)
                                 .findFirst()
                                 .orElse(0D);

            //TODO: DELETE THIS LATER OR CHANGE TO DYNAMIC
            Long hardCodeCartId = 1L;

            CartItem newCartItem = CartItem.builder()
                                           .id(CartItemKey
                                                   .builder()
                                                   .cartId(hardCodeCartId)
                                                   .productId(productId)
                                                   .build())
                                           .product(Product
                                                   .builder()
                                                   .id(productId)
                                                   .name(product.getName())
                                                   .supplier(product.getSupplier())
                                                   .build())
                                           .unitPrice(product.getUnitPrice())
                                           .quantity(quantity)
                                           .discount(discount)
                                           //TODO: dont do this, get cart from user id
                                           // later
                                           .cart(Cart.builder().id(hardCodeCartId).build())
                                           .build();
            cartItems.add(newCartItem);
        }

        List<CartItem> createdItems = cartItemRepository.saveAll(cartItems);

        return createdItems.stream().map((cartItem -> {
            CartItem data = this.findCartItemEntityById(this.mapCartItemKeyToDto(cartItem.getId()));

            return this.mapToCartItemResponseDto(
                    this.findCartItemEntityById(this.mapCartItemKeyToDto(data.getId()))
            );
        })).toList();
    }

    @Override
    public GetCartItemResponseDTO updateCartItemQuantity(CartItemKeyDTO id, Integer quantity) {
        CartItem foundCartItem = this.findCartItemEntityById(id);

        foundCartItem.setQuantity(quantity);

        return this.mapToCartItemResponseDto(cartItemRepository.save(foundCartItem));
    }

    @Override
    public void bulkDeleteItems(BulkDeleteItemsRequestDTO dto) {
        validateIfCartItemsExist(dto.getCartItemIds());

        cartItemRepository.deleteAllById(dto.getCartItemIds()
                                            .stream()
                                            .map(this::mapCartItemKeyToEntity)
                                            .toList()
        );
    }

    private void validateIfCartItemsExist(List<CartItemKeyDTO> ids) {
        List<CartItemKeyDTO> notFoundCartItemIds = new ArrayList<>();

        boolean someNotFound = false;

        for (CartItemKeyDTO id : ids) {
            if (!cartItemRepository.existsById(this.mapCartItemKeyToEntity(id))) {
                notFoundCartItemIds.add(id);
                someNotFound = true;
            }
        }

        if (someNotFound) {
            throw new BadRequestException("Some items not exist", notFoundCartItemIds);
        }
    }
}
