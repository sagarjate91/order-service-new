package com.orderservice.order_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orderservice.order_service.dto.OrderDto;
import com.orderservice.order_service.exception.OrderNotFoundException;
import com.orderservice.order_service.mapper.OrderConversion;
import com.orderservice.order_service.model.Order;
import com.orderservice.order_service.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    // Inject the OrderRepository
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConversion orderConversion;


    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        // Convert DTO to entity
       // Order order = orderConversion.toEntity(orderDto);

        // Save the order using the repository
        //Order savedOrder = orderRepository.save(order);

        // Convert the saved entity back to DTO
        return orderConversion.toDto(orderRepository.save(orderConversion.toEntity(orderDto)));
    }



}
