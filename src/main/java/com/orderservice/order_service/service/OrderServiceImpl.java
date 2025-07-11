package com.orderservice.order_service.service;

import java.util.List;

import com.orderservice.order_service.dto.OrderRequest;
import com.orderservice.order_service.dto.OrderResponse;
import com.orderservice.order_service.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;

import com.orderservice.order_service.dto.OrderDto;
import com.orderservice.order_service.exception.OrderNotFoundException;
import com.orderservice.order_service.mapper.OrderConversion;
import com.orderservice.order_service.model.Order;
import com.orderservice.order_service.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    // Inject the OrderRepository
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConversion orderConversion;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        // Convert DTO to entity
        Order order = orderConversion.toEntity(orderRequest.getOrderDto());

        // Save the order using the repository
        Order savedOrder = orderRepository.save(order);

        // Convert the saved entity back to DTO
        OrderDto orderDto=orderConversion.toDto(savedOrder);
        // Call the payment service to create a payment
        String paymentServiceUrl = "http://localhost:8082/api/payments/create"; // Adjust the URL as needed
        ResponseEntity<PaymentDto> paymentDtoResponseEntity = restTemplate.postForEntity(
                paymentServiceUrl,
                orderRequest.getPaymentDto(),
                PaymentDto.class
        );
        return new OrderResponse(orderDto,paymentDtoResponseEntity.getBody());
    }



}
