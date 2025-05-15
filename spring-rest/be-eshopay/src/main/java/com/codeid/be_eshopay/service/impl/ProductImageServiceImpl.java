package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.model.dto.FileMetaDataDTO;
import com.codeid.be_eshopay.model.dto.ProductDTO;
import com.codeid.be_eshopay.model.dto.ProductImageDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkDeleteProductImagesDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkUploadProductImagesDTO;
import com.codeid.be_eshopay.model.entity.FileMetaData;
import com.codeid.be_eshopay.model.entity.Product;
import com.codeid.be_eshopay.model.entity.ProductImage;
import com.codeid.be_eshopay.repository.ProductImageRepository;
import com.codeid.be_eshopay.service.FileStorageService;
import com.codeid.be_eshopay.service.ProductImageService;
import com.codeid.be_eshopay.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository productImageRepository;
    private final FileStorageService fileStorageService;
    private final ProductService productService;


    public static ProductImageDTO mapToDto(ProductImage productImage) {
        return ProductImageDTO.builder()
                              .id(productImage.getId())
                              .fileMetaData(FileMetaDataDTO
                                      .builder()
                                      .fileName(productImage.getFileMetaData().getFileName())
                                      .fileSize(productImage.getFileMetaData().getFileSize())
                                      .fileType(productImage.getFileMetaData().getFileType())
                                      .fileUri(productImage.getFileMetaData().getFileUri())
                                      .index(productImage.getFileMetaData().getIndex())
                                      .build())
                              .productId(productImage.getProduct().getId())
                              .build();
    }


    public static ProductImage mapToEntity(ProductImageDTO productImage) {
        return ProductImage.builder()
                           .id(productImage.getId())
                           .fileMetaData(FileMetaData
                                   .builder()
                                   .fileName(productImage.getFileMetaData().getFileName())
                                   .fileSize(productImage.getFileMetaData().getFileSize())
                                   .fileType(productImage.getFileMetaData().getFileType())
                                   .fileUri(productImage.getFileMetaData().getFileUri())
                                   .index(productImage.getFileMetaData().getIndex())
                                   .build())
                           .product(Product.builder().id(productImage.getProductId()).build())
                           .build();
    }


    @Override
    public List<ProductImageDTO> getAllProductImages(Long productId) {
        return productImageRepository.findByProductId(productId)
                                     .stream().map(ProductImageServiceImpl::mapToDto)
                                     .toList();
    }

    @Override
    public List<ProductImageDTO> uploadProductImages(Long productId, BulkUploadProductImagesDTO dto) {
        try {
            ProductDTO foundProduct = productService.findById(productId);

            List<FileMetaDataDTO> uploadedFiles = fileStorageService.bulkStoreFiles(dto.getData());

            List<ProductImage> productImages = uploadedFiles.stream()
                                                            .map((fileMetaDataDTO) -> {
                                                                return ProductImage.builder()
                                                                                   .fileMetaData(FileStorageServiceImpl.mapToEntity(fileMetaDataDTO))
                                                                                   .product(ProductServiceImpl.mapToEntity(foundProduct))
                                                                                   .build();
                                                            })
                                                            .toList();

            List<ProductImage> savedImages =
                    productImageRepository.saveAll(productImages);

            return savedImages.stream()
                              .map(ProductImageServiceImpl::mapToDto)
                              .toList();
        } catch (Exception e) {
            throw new BadRequestException("Upload files failed", null);
        }
    }

    @Override
    public void deleteProductImages(Long productId, BulkDeleteProductImagesDTO dto) {
        try {
            productService.findById(productId);

            List<ProductImage> foundImages =
                    productImageRepository.findByProductIdAndFileMetaDataFileUriIn(
                            productId,
                            dto.getData()
                    );

            productImageRepository.deleteAll(foundImages);
            fileStorageService.bulkDeleteFiles(dto.getData());
        } catch (Exception e) {
            throw new BadRequestException("Upload files failed", null);
        }
    }
}
