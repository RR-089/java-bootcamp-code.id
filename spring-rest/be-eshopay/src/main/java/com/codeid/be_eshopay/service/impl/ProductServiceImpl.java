package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.ProductDTO;
import com.codeid.be_eshopay.model.dto.response.product.GetProductResponseDTO;
import com.codeid.be_eshopay.model.entity.Product;
import com.codeid.be_eshopay.repository.ProductRepository;
import com.codeid.be_eshopay.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public static ProductDTO mapToDto(Product product) {
        return ProductDTO.builder()
                         .id(product.getId())
                         .name(product.getName())
                         .quantityPerUnit(product.getQuantityPerUnit())
                         .unitPrice(product.getUnitPrice())
                         .unitsInStock(product.getUnitsInStock())
                         .unitsOnOrder(product.getUnitsOnOrder())
                         .reorderLevel(product.getReorderLevel())
                         .discontinued(product.getDiscontinued())
                         .thumbnailPicture(product.getThumbnailPicture())
                         .category(CategoryServiceImpl.mapToDto(product.getCategory()))
                         .supplier(SupplierServiceImpl.mapToDto(product.getSupplier()))
                         .build();
    }

    public static Product mapToEntity(ProductDTO productDto) {
        return Product.builder()
                      .id(productDto.getId())
                      .name(productDto.getName())
                      .quantityPerUnit(productDto.getQuantityPerUnit())
                      .unitPrice(productDto.getUnitPrice())
                      .unitsInStock(productDto.getUnitsInStock())
                      .unitsOnOrder(productDto.getUnitsOnOrder())
                      .reorderLevel(productDto.getReorderLevel())
                      .discontinued(productDto.getDiscontinued())
                      .thumbnailPicture(productDto.getThumbnailPicture())
                      .category(CategoryServiceImpl.mapToEntity(productDto.getCategory()))
                      .supplier(SupplierServiceImpl.mapToEntity(productDto.getSupplier()))
                      .build();
    }

    private GetProductResponseDTO mapToProductResponseDTO(Product product) {
        return GetProductResponseDTO.builder()
                                    .id(product.getId())
                                    .name(product.getName())
                                    .unitsInStock(product.getUnitsInStock())
                                    .unitPrice(product.getUnitPrice())
                                    .thumbnailPicture(product.getThumbnailPicture())
                                    .build();
    }


    @Override
    public PaginationDTO<List<ProductDTO>> findAll(Pageable pageable) {
        log.debug("request fetching data products");

        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductDTO> productDtoList = productPage.stream()
                                                     .map(ProductServiceImpl::mapToDto)
                                                     .toList();

        return PaginationDTO.<List<ProductDTO>>builder()
                            .totalRecords(productPage.getTotalElements())
                            .data(productDtoList)
                            .build();
    }

    @Override
    public PaginationDTO<List<GetProductResponseDTO>> findAllWithSearch(String search, Pageable pageable) {
        log.debug("request fetching data products with search");

        Page<Product> productPage = productRepository.findAllWithSearch(search, pageable);

        List<GetProductResponseDTO> productDTOList = productPage
                .getContent()
                .stream()
                .map(this::mapToProductResponseDTO)
                .toList();

        return PaginationDTO.<List<GetProductResponseDTO>>builder()
                            .totalRecords(productPage.getTotalElements())
                            .data(productDTOList)
                            .build();
    }

    @Override
    public List<Product> findProductsByIds(List<Long> ids) {
        validateIfProductsExist(ids);

        return productRepository.findByIdIn(ids);
    }

    @Override
    public ProductDTO findById(Long id) {
        log.debug("request fetching data product id: {}", id);

        return productRepository
                .findById(id).map(ProductServiceImpl::mapToDto)
                .orElseThrow(
                        () -> new NotFoundException(
                                String.format("Product with id: %s not " +
                                                "found",
                                        id), null)
                );
    }

    @Override
    public ProductDTO create(ProductDTO entity) {
        log.debug("request to create product : {}", entity);

        return mapToDto(productRepository.save(mapToEntity(entity)));
    }

    @Override
    public ProductDTO update(Long id, ProductDTO entity) {
        log.debug("request to update product : {}", entity);

        Product foundProduct =
                productRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new BadRequestException(
                                        String.format("Product with id: %s does not " +
                                                "exist", id), null)
                        );

        log.debug("request to update existing product : {}", foundProduct);

        foundProduct.setName(entity.getName());
        foundProduct.setQuantityPerUnit(entity.getQuantityPerUnit());
        foundProduct.setUnitPrice(entity.getUnitPrice());
        foundProduct.setUnitsInStock(entity.getUnitsInStock());
        foundProduct.setUnitsOnOrder(entity.getUnitsOnOrder());
        foundProduct.setReorderLevel(entity.getReorderLevel());
        foundProduct.setDiscontinued(entity.getDiscontinued());
        foundProduct.setCategory(CategoryServiceImpl.mapToEntity(entity.getCategory()));
        foundProduct.setSupplier(SupplierServiceImpl.mapToEntity(entity.getSupplier()));

        return mapToDto(productRepository.save(foundProduct));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("request to delete product : {}", id);

        productRepository
                .findById(id)
                .orElseThrow(
                        () -> new BadRequestException(
                                String.format("Product with id: %s does not " +
                                        "exist", id), null)
                );

        productRepository.deleteById(id);
    }

    private void validateIfProductsExist(List<Long> ids) {
        List<Long> notFoundProductIds = new ArrayList<>();
        boolean someNotFound = false;

        for (Long id : ids) {
            if (!productRepository.existsById(id)) {
                notFoundProductIds.add(id);
                someNotFound = true;
            }
        }

        if (someNotFound) {
            throw new BadRequestException("Some products not exist", notFoundProductIds);
        }
    }
}
