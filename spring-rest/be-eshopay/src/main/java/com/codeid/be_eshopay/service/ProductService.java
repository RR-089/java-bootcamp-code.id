package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.ProductDTO;
import com.codeid.be_eshopay.model.dto.response.product.GetProductResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends BaseCrudService<ProductDTO, Long> {
    PaginationDTO<List<GetProductResponseDTO>> findAllWithSearch(String search, Pageable pageable);
}
