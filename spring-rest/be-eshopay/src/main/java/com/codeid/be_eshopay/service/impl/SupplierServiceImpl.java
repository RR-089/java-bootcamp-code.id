package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.SupplierDTO;
import com.codeid.be_eshopay.model.entity.Supplier;
import com.codeid.be_eshopay.repository.SupplierRepository;
import com.codeid.be_eshopay.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public static SupplierDTO mapToDto(Supplier supplier) {
        return SupplierDTO.builder()
                          .id(supplier.getId())
                          .companyName(supplier.getCompanyName())
                          .build();
    }

    public static Supplier mapToEntity(SupplierDTO supplierDTO) {
        return Supplier.builder()
                       .id(supplierDTO.getId())
                       .companyName(supplierDTO.getCompanyName())
                       .build();
    }

    @Override
    public List<SupplierDTO> findAll() {
        log.debug("request fetching data suppliers");

        return supplierRepository.findAll().stream()
                                 .map(SupplierServiceImpl::mapToDto)
                                 .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO findById(Long id) {
        log.debug("request fetching data supplier id: {}", id);

        return supplierRepository.findById(id)
                                 .map(SupplierServiceImpl::mapToDto)
                                 .orElseThrow(
                                         () -> new NotFoundException(String.format(
                                                 "Supplier with " +
                                                         "id: %s not found", id), null)
                                 );
    }

    @Override
    public SupplierDTO create(SupplierDTO entity) {
        log.debug("request to create supplier : {}", entity);

        return mapToDto(supplierRepository.save(mapToEntity(entity)));
    }

    @Override
    public SupplierDTO update(Long id, SupplierDTO entity) {
        log.debug("request to update supplier : {}", entity);

        Supplier foundSupplier = supplierRepository.findById(id)
                                                   .orElseThrow(() -> new BadRequestException(String.format("Supplier " +
                                                                   "with id: %s does " +
                                                                   "not exist",
                                                           id), null));

        log.debug("request to update existing supplier : {}", foundSupplier);

        foundSupplier.setCompanyName(entity.getCompanyName());

        return mapToDto(supplierRepository.save(foundSupplier));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("request to delete supplier : {}", id);

        supplierRepository.findById(id)
                          .orElseThrow(() -> new BadRequestException(String.format("Supplier " +
                                          "with id: %s does " +
                                          "not exist",
                                  id), null));

        supplierRepository.deleteById(id);
    }
}
