package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ProductImageDTO;
import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkDeleteProductImagesDTO;
import com.codeid.be_eshopay.model.dto.request.productimages.BulkUploadProductImagesDTO;
import com.codeid.be_eshopay.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductImageController {
    private final ProductImageService productImageService;

    @GetMapping("/{id}/images")
    public ResponseEntity<ResponseDTO<List<ProductImageDTO>>> findProductImages(@PathVariable(
            "id") Long id) {

        List<ProductImageDTO> data = productImageService.getAllProductImages(id);

        ResponseDTO<List<ProductImageDTO>> response = ResponseDTO.<List<ProductImageDTO>>builder()
                                                                 .status(HttpStatus.OK.value())
                                                                 .message("Get images " +
                                                                         "successful")
                                                                 .data(data)
                                                                 .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "{id}/bulk-upload", consumes = "multipart/form-data")
    public ResponseEntity<ResponseDTO<List<ProductImageDTO>>> bulkUploadProductImages(@PathVariable(
            "id") Long id, @Valid @ModelAttribute BulkUploadProductImagesDTO dto) {
        HttpStatus status = HttpStatus.CREATED;

        List<ProductImageDTO> data = productImageService.uploadProductImages(id, dto);

        ResponseDTO<List<ProductImageDTO>> response = ResponseDTO.<List<ProductImageDTO>>builder()
                                                                 .status(status.value())
                                                                 .message("Upload images successful")
                                                                 .data(data)
                                                                 .build();

        return ResponseEntity.status(status).body(response);
    }


    @DeleteMapping(value = "{id}/bulk-delete")
    public ResponseEntity<ResponseDTO<Object>> bulkDeleteProductImages(@PathVariable(
            "id") Long id, @Valid @RequestBody BulkDeleteProductImagesDTO dto) {

        productImageService.deleteProductImages(id, dto);

        ResponseDTO<Object> response = ResponseDTO.builder()
                                                  .status(HttpStatus.OK.value())
                                                  .message("Delete " +
                                                          "images successful")
                                                  .data(null)
                                                  .build();

        return ResponseEntity.ok(response);
    }


}
