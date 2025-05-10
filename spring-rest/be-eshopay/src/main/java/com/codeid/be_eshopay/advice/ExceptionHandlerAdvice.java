package com.codeid.be_eshopay.advice;

import com.codeid.be_eshopay.exception.CustomException;
import com.codeid.be_eshopay.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO<Object>> handleCustomException(CustomException ex) {
        final HttpStatus statusCode = ex.getStatus();

        log.debug(ex.getMessage());

        return ResponseEntity.status(statusCode).body(
                ResponseDTO.builder()
                           .status(statusCode.value())
                           .message(ex.getMessage())
                           .data(ex.getData())
                           .build()
        );
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseDTO<Object>> handleMaxSizeException(
            MaxUploadSizeExceededException ex) {

        final HttpStatus statusCode = HttpStatus.PAYLOAD_TOO_LARGE;

        return ResponseEntity.status(statusCode).body(
                ResponseDTO.builder()
                           .status(statusCode.value())
                           .message("The uploaded file exceeds the maximum allowed size")
                           .data(null)
                           .build()
        );
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Object>> handleValidationException(MethodArgumentNotValidException ex) {
        final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(statusCode).body(
                ResponseDTO.builder()
                           .status(statusCode.value())
                           .message("Validation failed")
                           .data(errors)
                           .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleGlobalException(Exception ex) {
        final HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(statusCode).body(
                ResponseDTO.builder()
                           .status(statusCode.value())
                           .message("An unexpected error occurred")
                           .data(null)
                           .build()
        );
    }

}
