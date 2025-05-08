package com.codeid.be_eshopay.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final Object data;
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public CustomException(HttpStatus status, String message, Object data) {
        super(message);
        this.data = data;
        this.status = status;
    }
}
