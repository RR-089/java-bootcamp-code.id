package com.codeid.eshopeer.service.impl;

import com.codeid.eshopeer.model.Shipper;
import com.codeid.eshopeer.repository.ShipperRepository;
import com.codeid.eshopeer.service.ShipperService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipperServiceImpl implements ShipperService {
    private final ShipperRepository shipperRepository;

    public ShipperServiceImpl(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }


    @Override
    public List<Shipper> findAllShipppers() {
        return shipperRepository.findAll();
    }

    @Override
    public Optional<Shipper> findShipperById(Long id) {
        return shipperRepository.findById(id);
    }

    @Override
    public Shipper saveShipper(Shipper shipper) {
        return shipperRepository.save(shipper);
    }

    @Override
    public void deleteShipper(Long id) {
        shipperRepository.deleteById(id);
    }
}
