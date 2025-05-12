package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.ProductDTO;
import com.codeid.be_eshopay.model.dto.SupplierDTO;
import com.codeid.be_eshopay.model.entity.Category;
import com.codeid.be_eshopay.model.entity.Product;
import com.codeid.be_eshopay.model.entity.Supplier;
import com.codeid.be_eshopay.repository.ProductRepository;
import com.codeid.be_eshopay.service.impl.CategoryServiceImpl;
import com.codeid.be_eshopay.service.impl.ProductServiceImpl;
import com.codeid.be_eshopay.service.impl.SupplierServiceImpl;
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
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testMapToDTO_Success() {
        Long mockId = 1L;

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockProduct =
                Product.builder()
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();

        ProductDTO result = ProductServiceImpl.mapToDto(mockProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockProduct.getId(), result.getId());
        Assertions.assertEquals(mockProduct.getName(), result.getName());
        Assertions.assertEquals(mockProduct.getQuantityPerUnit(), result.getQuantityPerUnit());
        Assertions.assertEquals(mockProduct.getUnitPrice(), result.getUnitPrice());
        Assertions.assertEquals(mockProduct.getUnitsInStock(), result.getUnitsInStock());
        Assertions.assertEquals(mockProduct.getUnitsOnOrder(), result.getUnitsOnOrder());
        Assertions.assertEquals(mockProduct.getReorderLevel(), result.getReorderLevel());
        Assertions.assertEquals(mockProduct.getDiscontinued(), result.getDiscontinued());
        Assertions.assertEquals(mockProduct.getThumbnailPicture(), result.getThumbnailPicture());

        Assertions.assertEquals(mockProduct.getCategory().getId(), result.getCategory().getId());
        Assertions.assertEquals(mockProduct.getCategory().getName(),
                result.getCategory().getName());

        Assertions.assertEquals(mockProduct.getSupplier().getId(),
                result.getSupplier().getId());

        Assertions.assertEquals(mockProduct.getSupplier().getCompanyName(),
                result.getSupplier().getCompanyName());

    }

    @Test
    void testMapToEntity_Success() {
        Long mockId = 1L;

        SupplierDTO mockSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP").build();

        CategoryDTO mockCategoryDto =
                CategoryDTO.builder().id(mockId).name("Beverage").build();

        ProductDTO mockProductDto =
                ProductDTO.builder()
                          .name("Pepsi")
                          .quantityPerUnit(200)
                          .unitPrice(1D)
                          .unitsInStock(180)
                          .unitsOnOrder(20)
                          .reorderLevel(50)
                          .discontinued((byte) 0)
                          .thumbnailPicture("thumb.png")
                          .category(mockCategoryDto)
                          .supplier(mockSupplierDto)
                          .build();

        Product result = ProductServiceImpl.mapToEntity(mockProductDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockProductDto.getId(), result.getId());
        Assertions.assertEquals(mockProductDto.getName(), result.getName());
        Assertions.assertEquals(mockProductDto.getQuantityPerUnit(), result.getQuantityPerUnit());
        Assertions.assertEquals(mockProductDto.getUnitPrice(), result.getUnitPrice());
        Assertions.assertEquals(mockProductDto.getUnitsInStock(), result.getUnitsInStock());
        Assertions.assertEquals(mockProductDto.getUnitsOnOrder(), result.getUnitsOnOrder());
        Assertions.assertEquals(mockProductDto.getReorderLevel(), result.getReorderLevel());
        Assertions.assertEquals(mockProductDto.getDiscontinued(), result.getDiscontinued());
        Assertions.assertEquals(mockProductDto.getThumbnailPicture(), result.getThumbnailPicture());

        Assertions.assertEquals(mockProductDto.getCategory().getId(), result.getCategory().getId());
        Assertions.assertEquals(mockProductDto.getCategory().getName(),
                result.getCategory().getName());

        Assertions.assertEquals(mockProductDto.getSupplier().getId(),
                result.getSupplier().getId());

        Assertions.assertEquals(mockProductDto.getSupplier().getCompanyName(),
                result.getSupplier().getCompanyName());

    }


    @Test
    void testFindAll_ReturnPaginationDTO() {
        Long mockId = 1L;

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockProduct =
                Product.builder()
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();

        List<Product> mockProducts = List.of(
                mockProduct
        );

        Page<Product> mockPage = new PageImpl<>(mockProducts);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(productRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<ProductDTO>> result =
                productService.findAll(mockPageable);


        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getData().size());

        for (int i = 0; i < mockProducts.size(); i++) {
            Product mockEntity = mockProducts.get(i);
            ProductDTO entityDto = result.getData().get(i);

            assertProductEquals(mockEntity, entityDto);
        }

        Mockito.verify(productRepository, Mockito.times(1))
               .findAll(mockPageable);
    }

    @Test
    void testFindAll_ReturnPaginationDTOEmptyList() {
        List<Product> mockProducts = new ArrayList<>();

        Page<Product> mockPage = new PageImpl<>(mockProducts);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(productRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<ProductDTO>> result =
                productService.findAll(mockPageable);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getData().isEmpty());

        Mockito.verify(productRepository, Mockito.times(1))
               .findAll(mockPageable);
    }

    @Test
    void testFindById_Success() {
        Long mockId = 1L;

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockProduct =
                Product.builder()
                       .id(mockId)
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();

        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.of(mockProduct));

        ProductDTO result = productService.findById(mockId);

        assertProductEquals(mockProduct, result);

        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);

    }

    @Test
    void testFindById_ProductNotFound() {
        Long mockId = 1L;

        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                () -> productService.findById(mockId));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        Assertions.assertEquals(String.format(
                "Product with id: %s not found", mockId), ex.getMessage()
        );

        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);
    }


    @Test
    void testCreateProduct_Success() {
        Long mockId = 1L;

        SupplierDTO mockSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP").build();

        CategoryDTO mockCategoryDto =
                CategoryDTO.builder().id(mockId).name("Beverage").build();

        ProductDTO mockInputProductDto =
                ProductDTO.builder()
                          .name("Pepsi")
                          .quantityPerUnit(200)
                          .unitPrice(1D)
                          .unitsInStock(180)
                          .unitsOnOrder(20)
                          .reorderLevel(50)
                          .discontinued((byte) 0)
                          .thumbnailPicture("thumb.png")
                          .category(mockCategoryDto)
                          .supplier(mockSupplierDto)
                          .build();

        ProductDTO mockExpectedProductDto =
                ProductDTO.builder()
                          .id(mockId)
                          .name("Pepsi")
                          .quantityPerUnit(200)
                          .unitPrice(1D)
                          .unitsInStock(180)
                          .unitsOnOrder(20)
                          .reorderLevel(50)
                          .discontinued((byte) 0)
                          .thumbnailPicture("thumb.png")
                          .category(mockCategoryDto)
                          .supplier(mockSupplierDto)
                          .build();


        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockProduct =
                Product.builder()
                       .id(mockId)
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();


        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(mockProduct);

        ProductDTO result = productService.create(mockInputProductDto);

        assertProductEquals(mockExpectedProductDto, result);

        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void testUpdateProduct_Success() {
        Long mockId = 1L;

        SupplierDTO mockSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP").build();

        CategoryDTO mockCategoryDto =
                CategoryDTO.builder().id(mockId).name("Beverage").build();

        ProductDTO mockInputProductDto =
                ProductDTO.builder()
                          .id(mockId)
                          .name("Pepsi updated")
                          .quantityPerUnit(2001)
                          .unitPrice(11D)
                          .unitsInStock(1801)
                          .unitsOnOrder(201)
                          .reorderLevel(501)
                          .discontinued((byte) 1)
                          .thumbnailPicture("thumb.png")
                          .category(mockCategoryDto)
                          .supplier(mockSupplierDto)
                          .build();


        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockExistingProduct =
                Product.builder()
                       .id(mockId)
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();


        Product mockUpdatedProduct =
                Product.builder()
                       .id(mockId)
                       .name("Pepsi updated")
                       .quantityPerUnit(2001)
                       .unitPrice(11D)
                       .unitsInStock(1801)
                       .unitsOnOrder(201)
                       .reorderLevel(501)
                       .discontinued((byte) 1)
                       .thumbnailPicture("thumb.png")
                       .category(CategoryServiceImpl.mapToEntity(mockCategoryDto))
                       .supplier(SupplierServiceImpl.mapToEntity(mockSupplierDto))
                       .build();


        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.of(mockExistingProduct));
        Mockito.when(productRepository.save(mockExistingProduct)).thenReturn(mockUpdatedProduct);


        ProductDTO result = productService.update(mockId, mockInputProductDto);

        assertProductEquals(mockInputProductDto, result);

        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }


    @Test
    void testUpdateProduct_BadRequest() {
        Long mockId = 1L;

        SupplierDTO mockSupplierDto =
                SupplierDTO.builder().id(mockId).companyName("HP").build();

        CategoryDTO mockCategoryDto =
                CategoryDTO.builder().id(mockId).name("Beverage").build();

        ProductDTO mockInputProductDto =
                ProductDTO.builder()
                          .id(mockId)
                          .name("Pepsi updated")
                          .quantityPerUnit(2001)
                          .unitPrice(11D)
                          .unitsInStock(1801)
                          .unitsOnOrder(201)
                          .reorderLevel(501)
                          .discontinued((byte) 1)
                          .thumbnailPicture("thumb.png")
                          .category(mockCategoryDto)
                          .supplier(mockSupplierDto)
                          .build();

        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> productService.update(mockId, mockInputProductDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Product with id: %s does not exist", mockId));

        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(productRepository, Mockito.times(0)).save(Mockito.any(Product.class));

    }

    @Test
    void testDeleteProduct_Success() {
        Long mockId = 1L;

        Supplier mockSupplier =
                Supplier.builder().id(mockId).companyName("HP").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Beverage").build();

        Product mockExistingProduct =
                Product.builder()
                       .id(mockId)
                       .name("Pepsi")
                       .quantityPerUnit(200)
                       .unitPrice(1D)
                       .unitsInStock(180)
                       .unitsOnOrder(20)
                       .reorderLevel(50)
                       .discontinued((byte) 0)
                       .thumbnailPicture("thumb.png")
                       .category(mockCategory)
                       .supplier(mockSupplier)
                       .build();

        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.of(mockExistingProduct));

        Mockito.doNothing().when(productRepository).deleteById(mockId);

        productService.deleteById(mockId);

        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(mockId);
    }

    @Test
    void testDeleteShipper_BadRequest() {
        Long mockId = 1L;

        Mockito.when(productRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> productService.deleteById(mockId));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Product with id: %s does not exist", mockId));


        Mockito.verify(productRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(productRepository, Mockito.times(0)).deleteById(mockId);
    }


    private void assertProductEquals(Product expected, ProductDTO actual) {
        Assertions.assertNotNull(actual, "Result should not be null");
        Assertions.assertEquals(expected.getId(), actual.getId(), "IDs should match");
        Assertions.assertEquals(expected.getName(), actual.getName(), "Names should match");
        Assertions.assertEquals(expected.getQuantityPerUnit(), actual.getQuantityPerUnit(), "Quantities per unit should match");
        Assertions.assertEquals(expected.getUnitPrice(), actual.getUnitPrice(), "Unit prices should match");
        Assertions.assertEquals(expected.getUnitsInStock(), actual.getUnitsInStock(), "Units in stock should match");
        Assertions.assertEquals(expected.getUnitsOnOrder(), actual.getUnitsOnOrder(), "Units on order should match");
        Assertions.assertEquals(expected.getReorderLevel(), actual.getReorderLevel(), "Reorder levels should match");
        Assertions.assertEquals(expected.getDiscontinued(), actual.getDiscontinued(), "Discontinued statuses should match");
        Assertions.assertEquals(expected.getThumbnailPicture(), actual.getThumbnailPicture(), "Thumbnail pictures should match");

        Assertions.assertEquals(expected.getCategory().getId(), actual.getCategory().getId(), "Category IDs should match");
        Assertions.assertEquals(expected.getCategory().getName(), actual.getCategory().getName(), "Category names should match");

        Assertions.assertEquals(expected.getSupplier().getId(), actual.getSupplier().getId(), "Supplier IDs should match");
        Assertions.assertEquals(expected.getSupplier().getCompanyName(), actual.getSupplier().getCompanyName(), "Supplier company names should match");
    }

    private void assertProductEquals(ProductDTO expected, ProductDTO actual) {
        Assertions.assertNotNull(actual, "Result should not be null");
        Assertions.assertEquals(expected.getId(), actual.getId(), "IDs should match");
        Assertions.assertEquals(expected.getName(), actual.getName(), "Names should match");
        Assertions.assertEquals(expected.getQuantityPerUnit(), actual.getQuantityPerUnit(), "Quantities per unit should match");
        Assertions.assertEquals(expected.getUnitPrice(), actual.getUnitPrice(), "Unit prices should match");
        Assertions.assertEquals(expected.getUnitsInStock(), actual.getUnitsInStock(), "Units in stock should match");
        Assertions.assertEquals(expected.getUnitsOnOrder(), actual.getUnitsOnOrder(), "Units on order should match");
        Assertions.assertEquals(expected.getReorderLevel(), actual.getReorderLevel(), "Reorder levels should match");
        Assertions.assertEquals(expected.getDiscontinued(), actual.getDiscontinued(), "Discontinued statuses should match");
        Assertions.assertEquals(expected.getThumbnailPicture(), actual.getThumbnailPicture(), "Thumbnail pictures should match");

        Assertions.assertEquals(expected.getCategory().getId(), actual.getCategory().getId(), "Category IDs should match");
        Assertions.assertEquals(expected.getCategory().getName(), actual.getCategory().getName(), "Category names should match");

        Assertions.assertEquals(expected.getSupplier().getId(), actual.getSupplier().getId(), "Supplier IDs should match");
        Assertions.assertEquals(expected.getSupplier().getCompanyName(), actual.getSupplier().getCompanyName(), "Supplier company names should match");
    }

}
