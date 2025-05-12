package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.ShipperDTO;
import com.codeid.be_eshopay.model.entity.Shipper;
import com.codeid.be_eshopay.repository.ShipperRepository;
import com.codeid.be_eshopay.service.impl.ShipperServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShipperServiceTests {

    @Mock
    private ShipperRepository shipperRepository;

    @InjectMocks
    private ShipperServiceImpl shipperService;

    @Test
    void testMapToDTO_Success() {
        Shipper mockShipper =
                Shipper.builder().id(1L).companyName("HP").phone("081").build();

        ShipperDTO result = ShipperServiceImpl.mapToDto(mockShipper);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockShipper.getId(), result.getId());
        Assertions.assertEquals(mockShipper.getCompanyName(), result.getCompanyName());
        Assertions.assertEquals(mockShipper.getPhone(), result.getPhone());
    }


    @Test
    void testMapToEntity_Success() {
        ShipperDTO mockShipperDto =
                ShipperDTO.builder().id(1L).companyName("HP").phone("081").build();

        Shipper result = ShipperServiceImpl.mapToEntity(mockShipperDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockShipperDto.getId(), result.getId());
        Assertions.assertEquals(mockShipperDto.getCompanyName(), result.getCompanyName());
        Assertions.assertEquals(mockShipperDto.getPhone(), result.getPhone());

    }


    @Test
    void testFindAll_ReturnPaginationDTO() {
        List<Shipper> mockShippers = List.of(
                Shipper.builder().id(1L).companyName("HP").phone("081").build(),
                Shipper.builder().id(2L).companyName("LG").phone("082").build(),
                Shipper.builder().id(3L).companyName("Sony").phone("083").build()
        );

        Page<Shipper> mockPage = new PageImpl<>(mockShippers);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(shipperRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<ShipperDTO>> result =
                shipperService.findAll(mockPageable);


        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.getData().size());

        for (int i = 0; i < mockShippers.size(); i++) {
            Shipper mockEntity = mockShippers.get(i);
            ShipperDTO entityDto = result.getData().get(i);

            Assertions.assertEquals(mockEntity.getId(), entityDto.getId());
            Assertions.assertEquals(mockEntity.getCompanyName(), entityDto.getCompanyName());
            Assertions.assertEquals(mockEntity.getPhone(), entityDto.getPhone());
        }

        Mockito.verify(shipperRepository, Mockito.times(1))
               .findAll(mockPageable);

    }

    @Test
    void testFindAll_ReturnPaginationDTOEmptyList() {
        List<Shipper> mockShippers = new ArrayList<>();
        Page<Shipper> mockPage = new PageImpl<>(mockShippers);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(shipperRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<ShipperDTO>> result =
                shipperService.findAll(mockPageable);


        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getData().isEmpty());

        Mockito.verify(shipperRepository, Mockito.times(1))
               .findAll(mockPageable);
    }

    @Test
    void testFindById_Success() {
        Long mockId = 1L;

        Shipper mockShipper =
                Shipper.builder().id(mockId).companyName("HP").phone("081").build();

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.of(mockShipper));

        ShipperDTO result = shipperService.findById(mockId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockShipper.getId(), result.getId());
        Assertions.assertEquals(mockShipper.getCompanyName(), result.getCompanyName());
        Assertions.assertEquals(mockShipper.getPhone(), result.getPhone());

        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);

    }

    @Test
    void testFindById_ShipperNotFound() {
        Long mockId = 1L;

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class, () -> shipperService.findById(mockId));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        Assertions.assertEquals(String.format(
                "Shipper with id: %s not found", mockId), ex.getMessage()
        );

        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);
    }

    @Test
    void testCreateShipper_Success() {
        Long mockId = 1L;

        ShipperDTO mockInputShipperDto =
                ShipperDTO.builder().companyName("HP").phone("081").build();

        ShipperDTO mockExpectedShipperDto =
                ShipperDTO.builder().id(mockId).companyName("HP").phone("081").build();

        Shipper mockShipper =
                Shipper.builder().id(mockId).companyName("HP").phone("081").build();

        Mockito.when(shipperRepository.save(Mockito.any(Shipper.class))).thenReturn(mockShipper);

        ShipperDTO result = shipperService.create(mockInputShipperDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedShipperDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedShipperDto.getCompanyName(), result.getCompanyName());
        Assertions.assertEquals(mockExpectedShipperDto.getPhone(), result.getPhone());

        Mockito.verify(shipperRepository, Mockito.times(1)).save(Mockito.any(Shipper.class));
    }

    @Test
    void testUpdateShipper_Success() {
        Long mockId = 1L;
        ShipperDTO mockInputShipperDto =
                ShipperDTO.builder().companyName("HP Extra").phone("0812").build();

        Shipper mockExistingShipper =
                Shipper.builder().id(mockId).companyName("HP").phone("081").build();

        Shipper mockUpdatedShipper =
                Shipper.builder().id(mockId).companyName("HP Extra").phone("0812").build();


        ShipperDTO mockExpectedShipperDto =
                ShipperDTO.builder().id(mockId).companyName("HP Extra").phone("0812").build();

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.of(mockExistingShipper));
        Mockito.when(shipperRepository.save(mockExistingShipper)).thenReturn(mockUpdatedShipper);


        ShipperDTO result = shipperService.update(mockId, mockInputShipperDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedShipperDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedShipperDto.getCompanyName(), result.getCompanyName());
        Assertions.assertEquals(mockExpectedShipperDto.getPhone(), result.getPhone());

        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(shipperRepository, Mockito.times(1)).save(Mockito.any(Shipper.class));
    }

    @Test
    void testUpdateShipper_BadRequest() {
        Long mockId = 1L;
        ShipperDTO mockInputShipperDto =
                ShipperDTO.builder().companyName("HP Extra").phone("0812").build();

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> shipperService.update(mockId, mockInputShipperDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Shipper with id: %s does not exist", mockId));

        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(shipperRepository, Mockito.times(0)).save(Mockito.any(Shipper.class));

    }

    @Test
    void testDeleteShipper_Success() {
        Long mockId = 1L;

        Shipper mockExistingShipper =
                Shipper.builder().id(mockId).companyName("HP").phone("081").build();

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.of(mockExistingShipper));

        Mockito.doNothing().when(shipperRepository).deleteById(mockId);

        shipperService.deleteById(mockId);

        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(shipperRepository, Mockito.times(1)).deleteById(mockId);
    }

    @Test
    void testDeleteShipper_BadRequest() {
        Long mockId = 1L;

        Mockito.when(shipperRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> shipperService.deleteById(mockId));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Shipper with id: %s does not exist", mockId));


        Mockito.verify(shipperRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(shipperRepository, Mockito.times(0)).deleteById(mockId);
    }

}
