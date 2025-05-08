package com.codeid.be_eshopay.controller;

import com.codeid.be_eshopay.model.dto.ResponseDTO;
import com.codeid.be_eshopay.service.BaseCrudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseCrudController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    protected abstract String getEntityName();

    protected String getEntityPluralName() {
        return getEntityName() + "s"; // Default behavior
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<T>>> getAll() {
        List<T> data = getService().findAll();

        ResponseDTO<List<T>> response = ResponseDTO.<List<T>>builder()
                                                   .status(HttpStatus.OK.value())
                                                   .message("Fetch " + getEntityPluralName() + " successful")
                                                   .data(data)
                                                   .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<T>> getById(@PathVariable("id") ID id) {
        T data = getService().findById(id);

        ResponseDTO<T> response = ResponseDTO.<T>builder()
                                             .status(HttpStatus.OK.value())
                                             .message("Fetch " + getEntityName() + " with id "
                                                     + id + " successful")
                                             .data(data)
                                             .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<T>> create(@Valid @RequestBody T entity) {
        T data = getService().create(entity);

        HttpStatus statusCode = HttpStatus.CREATED;

        ResponseDTO<T> response = ResponseDTO.<T>builder()
                                             .status(statusCode.value())
                                             .message("Create " + getEntityName()
                                                     + " successful")
                                             .data(data)
                                             .build();

        return ResponseEntity.status(statusCode).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<T>> update(@PathVariable("id") ID id,
                                                 @Valid @RequestBody T entity) {
        T data = getService().update(id, entity);

        ResponseDTO<T> response = ResponseDTO.<T>builder()
                                             .status(HttpStatus.OK.value())
                                             .message("Update " + getEntityName()
                                                     + " successful")
                                             .data(data)
                                             .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Object>> delete(@PathVariable("id") ID id) {
        getService().deleteById(id);

        return ResponseEntity.ok(
                ResponseDTO.builder()
                           .status(HttpStatus.OK.value())
                           .message("Delete " + getEntityName() + " with id: "
                                   + id + " is successful")
                           .data(null)
                           .build()
        );
    }
}
