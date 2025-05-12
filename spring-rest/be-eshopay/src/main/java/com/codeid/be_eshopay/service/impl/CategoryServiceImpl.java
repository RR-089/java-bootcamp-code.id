package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.request.CategoryWithPictureRequestDTO;
import com.codeid.be_eshopay.model.dto.response.CategoryWithPictureResponseDTO;
import com.codeid.be_eshopay.model.entity.Category;
import com.codeid.be_eshopay.repository.CategoryRepository;
import com.codeid.be_eshopay.service.CategoryService;
import com.codeid.be_eshopay.util.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public static CategoryDTO mapToDto(Category category) {
        return CategoryDTO.builder()
                          .id(category.getId())
                          .name(category.getName())
                          .description(category.getDescription())
                          .build();
    }

    public static Category mapToEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                       .id(categoryDTO.getId())
                       .name(categoryDTO.getName())
                       .description(categoryDTO.getDescription())
                       .build();
    }

    @Override
    public PaginationDTO<List<CategoryDTO>> findAll(Pageable pageable) {
        log.debug("request fetching data categories");

        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryDTO> categoryDtoList = categoryPage.getContent()
                                                        .stream().map(CategoryServiceImpl::mapToDto)
                                                        .toList();

        return PaginationDTO.<List<CategoryDTO>>builder()
                            .totalRecords(categoryPage.getTotalElements())
                            .data(categoryDtoList)
                            .build();
    }

    @Override
    public CategoryDTO findById(Long id) {
        log.debug("request fetching data category id: {}", id);

        return categoryRepository.findById(id)
                                 .map(CategoryServiceImpl::mapToDto)
                                 .orElseThrow(() -> new NotFoundException(String.format("Category with id: %s not found", id),
                                         null));
    }

    @Override
    public CategoryDTO create(CategoryDTO entity) {
        log.debug("request to create category : {}", entity);

        return mapToDto(categoryRepository.save(mapToEntity(entity)));
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO entity) {
        log.debug("request to update category : {}", entity);


        Category foundCategory = categoryRepository.findById(id).orElseThrow(
                () -> new BadRequestException(String.format("Category with id: %s does " +
                        "not exist", id),
                        null)
        );

        log.debug("request to update existing category : {}", foundCategory);


        foundCategory.setName(entity.getName());
        foundCategory.setDescription(entity.getDescription());

        return mapToDto(categoryRepository.save(foundCategory));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("request to delete category : {}", id);

        categoryRepository.findById(id).orElseThrow(
                () -> new BadRequestException(String.format("Category with id: %s does " +
                        "not exist", id), null)
        );

        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CategoryWithPictureResponseDTO createCategoryWithPicture(CategoryWithPictureRequestDTO request) {
        log.debug("request to create category with picture");

        try {
            Category newCategory = Category.builder()
                                           .name(request.getName())
                                           .description(request.getDescription())
                                           .picture(request.getPicture().getBytes()) // May throw IOException
                                           .build();

            Category createdCategory = categoryRepository.save(newCategory);

            return CategoryWithPictureResponseDTO.builder()
                                                 .id(createdCategory.getId())
                                                 .name(createdCategory.getName())
                                                 .description(createdCategory.getDescription())
                                                 .picture(ImageUtil.convertToBase64(createdCategory.getPicture()))
                                                 .build();
        } catch (IOException e) {
            log.debug("request to create category with picture failed : {}", e.getMessage());

            throw new BadRequestException("Failed to process the image", null);
        }
    }

    @Transactional
    @Override
    public CategoryWithPictureResponseDTO updateCategoryWithPicture(Long id, CategoryWithPictureRequestDTO request) {
        log.debug("request to update category with picture");

        try {
            Category foundCategory = categoryRepository.findById(id).orElseThrow(
                    () -> new BadRequestException(String.format("Category with id: %s " +
                            "not found", id), null)
            );

            foundCategory.setName(request.getName());
            foundCategory.setDescription(request.getDescription());

            if (request.getPicture() == null) {
                foundCategory.setPicture(null);
            } else {
                foundCategory.setPicture(request.getPicture().getBytes());
            }

            Category updatedCategory = categoryRepository.save(foundCategory);


            return CategoryWithPictureResponseDTO.builder()
                                                 .id(updatedCategory.getId())
                                                 .name(updatedCategory.getName())
                                                 .description(updatedCategory.getDescription())
                                                 .picture(ImageUtil.convertToBase64(updatedCategory.getPicture()))
                                                 .build();
        } catch (IOException e) {
            log.debug("request to update category with picture failed : {}",
                    e.getMessage());

            throw new BadRequestException("Failed to process the image", null);
        }
    }
}
