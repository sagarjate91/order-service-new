package com.orderservice.order_service.service;

import java.util.List;

import com.orderservice.order_service.dto.OrderDto;
import com.orderservice.order_service.exception.OrderNotFoundException;

public interface OrderService {

    // Define methods for order management
    OrderDto createOrder(OrderDto order);


}
