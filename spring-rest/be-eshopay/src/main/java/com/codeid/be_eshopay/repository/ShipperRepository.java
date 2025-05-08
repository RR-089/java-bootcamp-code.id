package com.codeid.be_eshopay.repository;

import com.codeid.be_eshopay.model.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
}
