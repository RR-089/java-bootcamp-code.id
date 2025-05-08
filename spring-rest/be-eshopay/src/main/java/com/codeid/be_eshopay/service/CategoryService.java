package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.dto.request.CategoryWithPictureRequestDTO;
import com.codeid.be_eshopay.model.dto.response.CategoryWithPictureResponseDTO;

public interface CategoryService extends BaseCrudService<CategoryDTO, Long> {
    CategoryWithPictureResponseDTO createCategoryWithPicture(CategoryWithPictureRequestDTO request);

    CategoryWithPictureResponseDTO updateCategoryWithPicture(Long id,
                                                             CategoryWithPictureRequestDTO request);

    ;
}
