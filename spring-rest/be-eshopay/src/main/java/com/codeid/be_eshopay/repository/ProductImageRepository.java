package com.codeid.be_eshopay.repository;

import com.codeid.be_eshopay.model.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductId(Long id);

    List<ProductImage> findByProductIdAndFileMetaDataFileUriIn(Long id,
                                                               List<String> fileUris);
}
