package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.BulkCreateItemsRequestDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.BulkDeleteItemsRequestDTO;
import com.codeid.be_eshopay.model.dto.request.cartitem.UpdateQuantityRequestDTO;
import com.codeid.be_eshopay.model.dto.response.cartitem.GetCartItemResponseDTO;
import com.codeid.be_eshopay.service.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts/cart-items")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<GetCartItemResponseDTO>>>
    getAllCartItems() {
        List<GetCartItemResponseDTO> data = cartItemService.findAllCartItems();

        ResponseDTO<List<GetCartItemResponseDTO>>
                response = ResponseDTO.<List<GetCartItemResponseDTO>>builder()
                                      .status(HttpStatus.OK.value())
                                      .message("Get items successful")
                                      .data(data)
                                      .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<List<GetCartItemResponseDTO>>>
    bulkCreateCartItems(@Valid @RequestBody BulkCreateItemsRequestDTO dto) {
        HttpStatus status = HttpStatus.CREATED;

        List<GetCartItemResponseDTO> data = cartItemService.bulkCreateItems(dto);


        ResponseDTO<List<GetCartItemResponseDTO>>
                response = ResponseDTO.<List<GetCartItemResponseDTO>>builder()
                                      .status(status.value())
                                      .message("Create items successful")
                                      .data(data)
                                      .build();

        return ResponseEntity.status(status).body(response);
    }

    @PatchMapping(value = "quantity")
    public ResponseEntity<ResponseDTO<GetCartItemResponseDTO>>
    updateCartItemQuantity(
            @Valid @RequestBody UpdateQuantityRequestDTO dto) {
        GetCartItemResponseDTO data = cartItemService
                .updateCartItemQuantity(dto.getCartItemId(), dto.getQuantity());

        ResponseDTO<GetCartItemResponseDTO>
                response = ResponseDTO.<GetCartItemResponseDTO>builder()
                                      .status(HttpStatus.OK.value())
                                      .message("Update items successful")
                                      .data(data)
                                      .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO<Object>> bulkDeleteCartItems(
            @Valid @RequestBody BulkDeleteItemsRequestDTO dto
    ) {
        cartItemService.bulkDeleteItems(dto);

        return ResponseEntity.ok(
                ResponseDTO.builder()
                           .status(HttpStatus.OK.value())
                           .message("Delete successful")
                           .data(null)
                           .build()
        );
    }
}
