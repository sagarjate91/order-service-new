package com.orderservice.order_service.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public String handleOrderNotFoundException(OrderNotFoundException ex) {
        return ex.getMessage();

    }

}
