package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.entity.Location;
import com.codeid.be_eshopay.repository.LocationRepository;
import com.codeid.be_eshopay.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;


    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Location not found", null));
    }
}
