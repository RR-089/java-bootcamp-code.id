package com.codeid.eshopeer.service;

import com.codeid.eshopeer.model.Shipper;

import java.util.List;
import java.util.Optional;

public interface ShipperService {
    List<Shipper> findAllShipppers();

    Optional<Shipper> findShipperById(Long id);

    Shipper saveShipper(Shipper shipper);

    void deleteShipper(Long id);
}
