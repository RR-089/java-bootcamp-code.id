package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.model.entity.OrderDetail;
import com.codeid.be_eshopay.repository.OrderDetailRepository;
import com.codeid.be_eshopay.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> createAll(List<OrderDetail> orderDetails) {
        return orderDetailRepository.saveAll(orderDetails);
    }
}
