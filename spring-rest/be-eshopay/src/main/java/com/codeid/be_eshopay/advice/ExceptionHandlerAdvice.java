package com.codeid.be_eshopay.advice;

import com.codeid.be_eshopay.exception.CustomException;
import com.codeid.be_eshopay.model.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
