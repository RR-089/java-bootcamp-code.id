package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.request.order.CreateOrderDTO;
import com.codeid.be_eshopay.model.dto.response.order.GetOrderByIdResponseDTO;
import com.codeid.be_eshopay.model.dto.response.order.GetOrderResponseDTO;

public interface OrderService {
    GetOrderResponseDTO createOrderData(CreateOrderDTO dto, boolean preOrder);

    GetOrderByIdResponseDTO findById(Long id);

    GetOrderResponseDTO saveOrderData(GetOrderResponseDTO poData);
}
