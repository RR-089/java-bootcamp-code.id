package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.SupplierDTO;
import com.codeid.be_eshopay.model.entity.Supplier;
import com.codeid.be_eshopay.repository.SupplierRepository;
import com.codeid.be_eshopay.service.impl.SupplierServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SupplierServiceTests {
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private SupplierServiceImpl supplierService;


    @Test
    void testMapToDTO_Success() {
        Supplier mockSupplier =
                Supplier.builder().id(1L).companyName("HP").build();

        SupplierDTO result = SupplierServiceImpl.mapToDto(mockSupplier);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockSupplier.getId(), result.getId());
        Assertions.assertEquals(mockSupplier.getCompanyName(), result.getCompanyName());
    }


    @Test
    void testMapToEntity_Success() {
        SupplierDTO mockSupplierDto =
                SupplierDTO.builder().id(1L).companyName("HP").build();

        Supplier result = SupplierServiceImpl.mapToEntity(mockSupplierDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockSupplierDto.getId(), result.getId());
        Assertions.assertEquals(mockSupplierDto.getCompanyName(), result.getCompanyName());
    }


    @Test
    void testFindAll_ReturnSupplierDTOList() {
        List<Supplier> mockSuppliers = List.of(
                Supplier.builder().id(1L).companyName("HP").build(),
                Supplier.builder().id(2L).companyName("LG").build(),
                Supplier.builder().id(3L).companyName("Sony").build()
        );

        Mockito.when(supplierRepository.findAll()).thenReturn(mockSuppliers);

        List<SupplierDTO> result = supplierService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());

        for (int i = 0; i < mockSuppliers.size(); i++) {
            Supplier mockEntity = mockSuppliers.get(i);
            SupplierDTO entityDto = result.get(i);

            Assertions.assertEquals(mockEntity.getId(), entityDto.getId());
            Assertions.assertEquals(mockEntity.getCompanyName(), entityDto.getCompanyName());
        }

        Mockito.verify(supplierRepository, Mockito.times(1)).findAll();
    }


    @Test
    void testFindAll_ReturnEmptyList() {
        List<Supplier> mockSuppliers = new ArrayList<>();
        Mockito.when(supplierRepository.findAll()).thenReturn(mockSuppliers);

        List<SupplierDTO> result = supplierService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());

        Mockito.verify(supplierRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        Long mockId = 1L;

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.of(mockSupplier));

        SupplierDTO result = supplierService.findById(mockId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockSupplier.getId(), result.getId());
        Assertions.assertEquals(mockSupplier.getCompanyName(), result.getCompanyName());

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);

    }

    @Test
    void testFindById_SupplierNotFound() {
        Long mockId = 1L;

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                () -> supplierService.findById(mockId));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        Assertions.assertEquals(String.format(
                "Supplier with id: %s not found", mockId), ex.getMessage()
        );

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);
    }

    @Test
    void testCreateSupplier_Success() {
        Long mockId = 1L;

        SupplierDTO mockInputSupplierDto =
                SupplierDTO.builder().companyName("HP").build();

        SupplierDTO mockExpectedSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP").build();

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(mockSupplier);

        SupplierDTO result = supplierService.create(mockInputSupplierDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedSupplierDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedSupplierDto.getCompanyName(), result.getCompanyName());

        Mockito.verify(supplierRepository, Mockito.times(1)).save(Mockito.any(Supplier.class));
    }

    @Test
    void testUpdateSupplier_Success() {
        Long mockId = 1L;
        SupplierDTO mockInputSupplierDto =
                SupplierDTO.builder().companyName("HP Extra").build();

        Supplier mockExistingSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Supplier mockUpdatedSupplier =
                Supplier.builder().id(mockId).companyName("HP Extra").build();


        SupplierDTO mockExpectedSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP Extra").build();

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.of(mockExistingSupplier));
        Mockito.when(supplierRepository.save(mockExistingSupplier)).thenReturn(mockUpdatedSupplier);


        SupplierDTO result = supplierService.update(mockId, mockInputSupplierDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedSupplierDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedSupplierDto.getCompanyName(), result.getCompanyName());

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(supplierRepository, Mockito.times(1)).save(Mockito.any(Supplier.class));
    }

    @Test
    void testUpdateSupplier_BadRequest() {
        Long mockId = 1L;
        SupplierDTO mockInputSupplierDto =
                SupplierDTO.builder().companyName("HP Extra").build();

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> supplierService.update(mockId, mockInputSupplierDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Supplier with id: %s does not exist", mockId));

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(supplierRepository, Mockito.times(0)).save(Mockito.any(Supplier.class));

    }

    @Test
    void testDeleteSupplier_Success() {
        Long mockId = 1L;

        Supplier mockExistingSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.of(mockExistingSupplier));

        Mockito.doNothing().when(supplierRepository).deleteById(mockId);

        supplierService.deleteById(mockId);

        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(supplierRepository, Mockito.times(1)).deleteById(mockId);
    }

    @Test
    void testDeleteSupplier_BadRequest() {
        Long mockId = 1L;

        Mockito.when(supplierRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> supplierService.deleteById(mockId));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Supplier with id: %s does not exist", mockId));


        Mockito.verify(supplierRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(supplierRepository, Mockito.times(0)).deleteById(mockId);
    }

}
