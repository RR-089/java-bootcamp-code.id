package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.exception.BadRequestException;
import com.codeid.be_eshopay.exception.NotFoundException;
import com.codeid.be_eshopay.model.dto.CategoryDTO;
import com.codeid.be_eshopay.model.dto.PaginationDTO;
import com.codeid.be_eshopay.model.dto.request.CategoryWithPictureRequestDTO;
import com.codeid.be_eshopay.model.dto.response.CategoryWithPictureResponseDTO;
import com.codeid.be_eshopay.model.entity.Category;
import com.codeid.be_eshopay.repository.CategoryRepository;
import com.codeid.be_eshopay.service.impl.CategoryServiceImpl;
import com.codeid.be_eshopay.util.ImageUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;


    @Test
    void testMapToDTO_Success() {
        Category mockCategory =
                Category.builder().id(1L).name("Electronic").build();

        CategoryDTO result = CategoryServiceImpl.mapToDto(mockCategory);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockCategory.getId(), result.getId());
        Assertions.assertEquals(mockCategory.getName(), result.getName());
    }


    @Test
    void testMapToEntity_Success() {
        CategoryDTO mockCategoryDto =
                CategoryDTO.builder().id(1L).name("Electronic").build();

        Category result = CategoryServiceImpl.mapToEntity(mockCategoryDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockCategoryDto.getId(), result.getId());
        Assertions.assertEquals(mockCategoryDto.getName(), result.getName());
    }


    @Test
    void testFindAll_ReturnPaginationDTO() {

        List<Category> mockCategories = List.of(
                Category.builder().id(1L).name("Electronic").description("this is " +
                        "electronic").build(),
                Category.builder().id(2L).name("Food").description("this is food").build(),
                Category.builder().id(3L).name("Beverage").description("this is " +
                        "beverage").build()
        );

        Page<Category> mockPage = new PageImpl<>(mockCategories);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(categoryRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<CategoryDTO>> result = categoryService.findAll(mockPageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.getData().size());

        for (int i = 0; i < mockCategories.size(); i++) {
            Category mockEntity = mockCategories.get(i);
            CategoryDTO entityDto = result.getData().get(i);

            Assertions.assertEquals(mockEntity.getId(), entityDto.getId());
            Assertions.assertEquals(mockEntity.getName(), entityDto.getName());
            Assertions.assertEquals(mockEntity.getDescription(), entityDto.getDescription());
        }

        Mockito.verify(categoryRepository, Mockito.times(1))
               .findAll(mockPageable);
    }


    @Test
    void testFindAll_ReturnPaginationDTOEmptyList() {
        List<Category> mockCategories = new ArrayList<>();

        Page<Category> mockPage = new PageImpl<>(mockCategories);
        Pageable mockPageable = PageRequest.of(0, 3);

        Mockito.when(categoryRepository.findAll(mockPageable)).thenReturn(mockPage);

        PaginationDTO<List<CategoryDTO>> result = categoryService.findAll(mockPageable);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getData().isEmpty());

        Mockito.verify(categoryRepository, Mockito.times(1))
               .findAll(mockPageable);
    }


    @Test
    void testFindById_Success() {
        Long mockId = 1L;

        Category mockCategory =
                Category.builder().id(1L).name("Electronic").description("this is " +
                        "electronic").build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockCategory));

        CategoryDTO result = categoryService.findById(mockId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockCategory.getId(), result.getId());
        Assertions.assertEquals(mockCategory.getName(), result.getName());
        Assertions.assertEquals(mockCategory.getDescription(), result.getDescription());

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);

    }

    @Test
    void testFindById_CategoryNotFound() {
        Long mockId = 1L;

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.empty());

        NotFoundException ex = Assertions.assertThrows(NotFoundException.class,
                () -> categoryService.findById(mockId));

        Assertions.assertEquals(HttpStatus.NOT_FOUND, ex.getStatus());
        Assertions.assertEquals(String.format(
                "Category with id: %s not found", mockId), ex.getMessage()
        );

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
    }

    @Test
    void testCreateCategory_Success() {
        Long mockId = 1L;

        CategoryDTO mockInputCategoryDto =
                CategoryDTO.builder().name("Electronic").description("this is " +
                        "electronic").build();

        CategoryDTO mockExpectedCategoryDto =
                CategoryDTO.builder().id(mockId).name("Electronic").description("this is " +
                        "electronic").build();

        Category mockCategory =
                Category.builder().id(mockId).name("Electronic").description("this is " +
                        "electronic").build();

        Mockito.when(categoryRepository.save(Mockito.any(Category.class))).thenReturn(mockCategory);

        CategoryDTO result = categoryService.create(mockInputCategoryDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedCategoryDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedCategoryDto.getName(), result.getName());
        Assertions.assertEquals(mockExpectedCategoryDto.getDescription(), result.getDescription());

        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testUpdateCategory_Success() {
        Long mockId = 1L;
        CategoryDTO mockInputCategoryDto =
                CategoryDTO.builder().name("Electronic").description("this is " +
                        "electronic").build();

        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic old").description("this " +
                        "is " +
                        "electronic old").build();

        Category mockUpdatedCategory =
                Category.builder().id(mockId).name("Electronic").description("this is " +
                        "electronic").build();

        CategoryDTO mockExpectedCategoryDto =
                CategoryDTO.builder().id(mockId).name("Electronic").description("this is " +
                        "electronic").build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockExistingCategory));
        Mockito.when(categoryRepository.save(mockExistingCategory)).thenReturn(mockUpdatedCategory);


        CategoryDTO result = categoryService.update(mockId, mockInputCategoryDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockExpectedCategoryDto.getId(), result.getId());
        Assertions.assertEquals(mockExpectedCategoryDto.getName(), result.getName());
        Assertions.assertEquals(mockExpectedCategoryDto.getDescription(), result.getDescription());

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testUpdateCategory_BadRequest() {
        Long mockId = 1L;
        CategoryDTO mockInputCategoryDto =
                CategoryDTO.builder().name("Electronic").description("this is " +
                        "electronic").build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> categoryService.update(mockId, mockInputCategoryDto));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Category with id: %s does not exist", mockId));

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any(Category.class));

    }

    @Test
    void testDeleteCategory_Success() {
        Long mockId = 1L;

        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic old").description("this " +
                        "is " +
                        "electronic old").build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockExistingCategory));

        Mockito.doNothing().when(categoryRepository).deleteById(mockId);

        categoryService.deleteById(mockId);

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(mockId);
    }

    @Test
    void testDeleteShipper_BadRequest() {
        Long mockId = 1L;

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> categoryService.deleteById(mockId));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(ex.getMessage(),
                String.format("Category with id: %s does not exist", mockId));


        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(0)).deleteById(mockId);
    }

    @Test
    void testCreateCategoryWithPicture_Success() throws IOException {
        Long mockId = 1L;

        MultipartFile mockPicture = Mockito.mock(MultipartFile.class);

        byte[] mockPictureBytes = "Image Bytes".getBytes();

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(mockPicture).build();

        Mockito.when(mockPicture.getBytes()).thenReturn(mockPictureBytes);

        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic")
                        .description("this is electronic")
                        .picture("Image Bytes".getBytes())
                        .build();

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
               .thenReturn(mockExistingCategory);

        CategoryWithPictureResponseDTO result = categoryService.createCategoryWithPicture(mockRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockId, result.getId());
        Assertions.assertEquals(mockRequest.getName(), result.getName());
        Assertions.assertEquals(mockRequest.getDescription(), result.getDescription());
        Assertions.assertEquals(ImageUtil.convertToBase64(mockPictureBytes), result.getPicture());

        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testCreateCategoryWithPicture_BadRequest() throws IOException {

        MultipartFile mockPicture = Mockito.mock(MultipartFile.class);

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(mockPicture).build();

        Mockito.when(mockPicture.getBytes()).thenThrow(new IOException("Simulate error"));

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> categoryService.createCategoryWithPicture(mockRequest)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals("Failed to process the image", ex.getMessage());
        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any(Category.class));
    }

    @Test
    void testUpdateCategoryWithPicture_Success() throws IOException {
        Long mockId = 1L;

        MultipartFile mockPicture = Mockito.mock(MultipartFile.class);
        byte[] mockPictureBytes = "Image Bytes New".getBytes();

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(mockPicture).build();

        Mockito.when(mockPicture.getBytes()).thenReturn(mockPictureBytes);

        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic old")
                        .description("this is electronic old")
                        .picture("Image Bytes Old".getBytes())
                        .build();

        Category mockUpdatedCategory =
                Category.builder().id(mockId).name("Electronic")
                        .description("this is electronic")
                        .picture(mockPictureBytes)
                        .build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockExistingCategory));

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
               .thenReturn(mockUpdatedCategory);

        CategoryWithPictureResponseDTO result =
                categoryService.updateCategoryWithPicture(mockId, mockRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockUpdatedCategory.getId(), result.getId());
        Assertions.assertEquals(mockUpdatedCategory.getName(), result.getName());
        Assertions.assertEquals(mockUpdatedCategory.getDescription(), result.getDescription());
        Assertions.assertEquals(ImageUtil.convertToBase64(mockPictureBytes),
                result.getPicture());

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testUpdateCategoryWithPicture_SuccessDeletePicture() {
        Long mockId = 1L;

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(null).build();


        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic old")
                        .description("this is electronic old")
                        .picture("Image Bytes Old".getBytes())
                        .build();

        Category mockUpdatedCategory =
                Category.builder().id(mockId).name("Electronic")
                        .description("this is electronic")
                        .picture(null)
                        .build();

        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockExistingCategory));

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
               .thenReturn(mockUpdatedCategory);

        CategoryWithPictureResponseDTO result =
                categoryService.updateCategoryWithPicture(mockId, mockRequest);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(mockUpdatedCategory.getId(), result.getId());
        Assertions.assertEquals(mockUpdatedCategory.getName(), result.getName());
        Assertions.assertEquals(mockUpdatedCategory.getDescription(), result.getDescription());
        Assertions.assertEquals(ImageUtil.convertToBase64(null),
                result.getPicture());

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    void testUpdateCategoryWithPicture_BadRequestNoCategoryMatches() {
        Long mockId = 1L;

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(null).build();


        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.empty());

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> categoryService.updateCategoryWithPicture(mockId, mockRequest)
        );


        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals(
                String.format("Category with id: %s not found", mockId), ex.getMessage()
        );

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any(Category.class));
    }


    @Test
    void testUpdateCategoryWithPicture_BadRequestPictureField() throws IOException {
        Long mockId = 1L;

        MultipartFile mockPicture = Mockito.mock(MultipartFile.class);

        CategoryWithPictureRequestDTO mockRequest =
                CategoryWithPictureRequestDTO.builder().name("Electronic").description("this is " +
                        "electronic").picture(mockPicture).build();

        Category mockExistingCategory =
                Category.builder().id(mockId).name("Electronic old")
                        .description("this is electronic old")
                        .picture("Image Bytes Old".getBytes())
                        .build();

        Mockito.when(mockPicture.getBytes()).thenThrow(new IOException("Simulate error"));
        Mockito.when(categoryRepository.findById(mockId)).thenReturn(Optional.of(mockExistingCategory));

        BadRequestException ex = Assertions.assertThrows(BadRequestException.class,
                () -> categoryService.updateCategoryWithPicture(mockId, mockRequest)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
        Assertions.assertEquals("Failed to process the image", ex.getMessage());

        Mockito.verify(categoryRepository, Mockito.times(1)).findById(mockId);
        Mockito.verify(categoryRepository, Mockito.times(0)).save(Mockito.any(Category.class));
    }
}
