package com.orderservice.order_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity.HeadersBuilder<?> handleOrderNotFoundException(OrderNotFoundException ex) {
        return ResponseEntity.notFound();

    }

}
