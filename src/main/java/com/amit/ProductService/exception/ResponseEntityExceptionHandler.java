package com.amit.ProductService.exception;

import com.amit.ProductService.model.ErrorResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductServiceCustomException.class)
    public HttpEntity<ErrorResponse> HandleProductServiceException(ProductServiceCustomException exception) {
        return new ResponseEntity<>(new com.amit.ProductService.model.ErrorResponse().builder()
                .errorCode(exception.getErrorCode())
                .errorMessage(exception.getMessage())
                .build(), HttpStatus.NOT_FOUND);
    }
}
