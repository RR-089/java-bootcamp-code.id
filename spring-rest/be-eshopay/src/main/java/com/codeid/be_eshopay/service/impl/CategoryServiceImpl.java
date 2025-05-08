package com.codeid.be_eshopay.service.impl;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.entity.Category;
import com.codeid.be_eshopay.repository.CategoryRepository;
import com.codeid.be_eshopay.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CategoryDTO> findAll() {
        log.debug("request fetching data categories");

        return categoryRepository.findAll().stream()
                                 .map(CategoryServiceImpl::mapToDto)
                                 .collect(Collectors.toList());
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
                        "not exists", id),
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
                        "not exists", id), null)
        );

        categoryRepository.deleteById(id);
    }
}
