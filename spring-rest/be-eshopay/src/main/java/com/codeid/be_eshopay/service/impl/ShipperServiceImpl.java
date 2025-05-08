package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.ShipperDTO;
import com.codeid.be_eshopay.model.entity.Shipper;
import com.codeid.be_eshopay.repository.ShipperRepository;
import com.codeid.be_eshopay.service.ShipperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipperServiceImpl implements ShipperService {
    private final ShipperRepository shipperRepository;

    public static ShipperDTO mapToDto(Shipper shipper) {
        return ShipperDTO.builder()
                         .id(shipper.getId())
                         .companyName(shipper.getCompanyName())
                         .phone(shipper.getPhone())
                         .build();
    }

    public static Shipper mapToEntity(ShipperDTO shipperDTO) {
        return Shipper.builder()
                      .id(shipperDTO.getId())
                      .companyName(shipperDTO.getCompanyName())
                      .phone(shipperDTO.getPhone())
                      .build();
    }


    @Override
    public List<ShipperDTO> findAll() {
        log.debug("request fetching data shippers");

        return shipperRepository.findAll().stream().map(ShipperServiceImpl::mapToDto)
                                .collect(Collectors.toList());
    }

    @Override
    public ShipperDTO findById(Long id) {
        log.debug("request fetching data shipper id: {}", id);

        return shipperRepository.findById(id).map(ShipperServiceImpl::mapToDto)
                                .orElseThrow(() -> new NotFoundException(String.format(
                                        "Shipper with " +
                                                "id: %s not found", id), null));
    }

    @Override
    public ShipperDTO create(ShipperDTO entity) {
        log.debug("request to create shipper : {}", entity);

        return mapToDto(shipperRepository.save(mapToEntity(entity)));
    }

    @Override
    public ShipperDTO update(Long id, ShipperDTO entity) {
        log.debug("request to update shipper : {}", entity);


        Shipper foundShipper = shipperRepository.findById(id).orElseThrow(
                () -> new BadRequestException(String.format("Shipper with id: %s does " +
                        "not exist", id), null)
        );

        log.debug("request to update existing shipper : {}", foundShipper);

        foundShipper.setCompanyName(entity.getCompanyName());
        foundShipper.setPhone(entity.getPhone());

        return mapToDto(shipperRepository.save(foundShipper));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("request to delete shipper : {}", id);

        shipperRepository.findById(id).orElseThrow(
                () -> new BadRequestException(String.format("Shipper with id: %s does " +
                        "not exist", id), null)
        );

        shipperRepository.deleteById(id);
    }
}
