package com.codeid.be_eshopay.repository;

import com.codeid.be_eshopay.model.entity.OrderDetail;
import com.codeid.be_eshopay.model.entity.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}
