package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ProductDTO;
import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import com.codeid.be_eshopay.service.FileStorageService;
import com.codeid.be_eshopay.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController extends BaseMultipartAndCrudController<ProductDTO, Long> {
    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Override
    protected BaseCrudService<ProductDTO, Long> getService() {
        return productService;
    }

    @Override
    protected String getEntityName() {
        return "product";
    }

    @Override
    public ResponseEntity<ResponseDTO<?>> createMultipart(ProductDTO dto, MultipartFile file, String description) {
        try {
            HttpStatus statusCode = HttpStatus.CREATED;

            String fileName = fileStorageService.storeFileWithRandomName(file);

            dto.setThumbnailPicture(fileName);
            ProductDTO productDto = productService.create(dto);

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                                                          .status(statusCode.value())
                                                          .message("Upload " + getEntityName()
                                                                  + " thumbnail image " +
                                                                  "successful")
                                                          .data(productDto)
                                                          .build();

            return ResponseEntity.status(statusCode).body(response);
        } catch (Exception e) {
            HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                                                          .status(statusCode.value())
                                                          .message("Save image failed")
                                                          .data(null)
                                                          .build();


            return ResponseEntity.status(statusCode).body(response);
        }
    }

    @Override
    public ResponseEntity<?> viewImage(String fileName) {
        try {
            Resource resource = fileStorageService.loadFile(fileName);

            String contentType = determineContentType(fileName);

            return ResponseEntity.ok()
                                 .contentType(MediaType.parseMediaType(contentType))
                                 .header(HttpHeaders.CONTENT_DISPOSITION,
                                         "inline; filename=\"" + resource.getFilename() + "\"")
                                 .body(resource);
        } catch (Exception e) {
            HttpStatus statusCode = HttpStatus.BAD_REQUEST;

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                                                          .status(statusCode.value())
                                                          .message("Image not found")
                                                          .data(null)
                                                          .build();

            return ResponseEntity.status(statusCode).body(response);
        }
    }

    @Override
    public ResponseEntity<ResponseDTO<?>> updateMultipart(Long id, ProductDTO dto, MultipartFile file
            , String description) {
        try {
            String fileName = null;

            ProductDTO foundProduct = productService.findById(id);

            if (!file.isEmpty()) {
                fileName = fileStorageService.storeFileWithRandomName(file);
                fileStorageService.deleteFile(foundProduct.getThumbnailPicture());
            }

            fileStorageService.deleteFile(foundProduct.getThumbnailPicture());

            dto.setThumbnailPicture(fileName);

            ProductDTO productDto = productService.create(dto);

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                                                          .status(HttpStatus.OK.value())
                                                          .message("Update " + getEntityName()
                                                                  + " thumbnail image " +
                                                                  "successful")
                                                          .data(productDto)
                                                          .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            ResponseDTO<ProductDTO> response = ResponseDTO.<ProductDTO>builder()
                                                          .status(statusCode.value())
                                                          .message("Save image failed")
                                                          .data(null)
                                                          .build();


            return ResponseEntity.status(statusCode).body(response);
        }
    }


}
