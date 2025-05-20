package com.codeid.be_eshopay.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends CustomException {
    public InternalServerException(String message, Object data) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, data);
    }
}
