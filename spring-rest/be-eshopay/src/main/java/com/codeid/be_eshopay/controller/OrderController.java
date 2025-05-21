package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.model.dto.request.order.CreateOrderDTO;
import com.codeid.be_eshopay.model.dto.response.order.GetOrderByIdResponseDTO;
import com.codeid.be_eshopay.model.dto.response.order.GetOrderResponseDTO;
import com.codeid.be_eshopay.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<ResponseDTO<GetOrderByIdResponseDTO>> createOrder(
            @PathVariable("id") Long id
    ) {

        GetOrderByIdResponseDTO data = orderService.findById(id);

        ResponseDTO<GetOrderByIdResponseDTO> response =
                ResponseDTO.<GetOrderByIdResponseDTO>builder()
                           .status(HttpStatus.OK.value())
                           .message("Get order data successful")
                           .data(data)
                           .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<GetOrderByIdResponseDTO>> createOrder(
            @Valid @RequestBody CreateOrderDTO dto
    ) {
        HttpStatus status = HttpStatus.CREATED;

        GetOrderByIdResponseDTO data = orderService.saveOrderData(dto);

        ResponseDTO<GetOrderByIdResponseDTO> response =
                ResponseDTO.<GetOrderByIdResponseDTO>builder()
                           .status(status.value())
                           .message("Create order data successful")
                           .data(data)
                           .build();

        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/pre-order")
    public ResponseEntity<ResponseDTO<GetOrderResponseDTO>> createPreOrderData(
            @Valid @RequestBody CreateOrderDTO dto
    ) {
        HttpStatus status = HttpStatus.CREATED;

        GetOrderResponseDTO data = orderService.createOrderData(dto);

        ResponseDTO<GetOrderResponseDTO> response =
                ResponseDTO.<GetOrderResponseDTO>builder()
                           .status(status.value())
                           .message("Create pre order data successful")
                           .data(data)
                           .build();

        return ResponseEntity.status(status).body(response);
    }
}
