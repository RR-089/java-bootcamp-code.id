package com.codeid.be_eshopay.service;


import com.codeid.be_eshopay.model.dto.ProductImageDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkDeleteProductImagesDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkUploadProductImagesDTO;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDTO> getAllProductImages(Long productId);

    List<ProductImageDTO> uploadProductImages(Long productId,
                                              BulkUploadProductImagesDTO dto);

    void deleteProductImages(Long productId, BulkDeleteProductImagesDTO dto);
}
