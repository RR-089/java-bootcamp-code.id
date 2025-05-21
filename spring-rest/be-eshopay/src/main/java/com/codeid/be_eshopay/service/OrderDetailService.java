package com.codeid.be_eshopay.service;


import com.codeid.be_eshopay.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> createAll(List<OrderDetail> orderDetails);
}
