package com.orderservice.order_service.exception;

public class OrderNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
