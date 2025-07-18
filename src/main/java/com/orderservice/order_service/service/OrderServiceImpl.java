package com.orderservice.order_service.service;


import com.orderservice.order_service.client.PaymentClient;
import com.orderservice.order_service.dto.OrderRequest;
import com.orderservice.order_service.dto.OrderResponse;
import com.orderservice.order_service.dto.PaymentDto;
import com.orderservice.order_service.exception.OrderNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;

import com.orderservice.order_service.dto.OrderDto;
import com.orderservice.order_service.mapper.OrderConversion;
import com.orderservice.order_service.model.Order;
import com.orderservice.order_service.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {


    // Inject the OrderRepository
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderConversion orderConversion;

    @Autowired
    private PaymentClient paymentClient;


    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        // Convert DTO to entity
        Order order = orderConversion.toEntity(orderRequest.getOrderDto());

        // Save the order using the repository
        Order savedOrder = orderRepository.save(order);

        // Convert the saved entity back to DTO
        OrderDto orderDto=orderConversion.toDto(savedOrder);
        // Call the payment service to create a payment
        ResponseEntity<PaymentDto> paymentDtoResponseEntity = paymentClient.createPayment(orderRequest.getPaymentDto());
        return new OrderResponse(orderDto,paymentDtoResponseEntity.getBody());
    }

    @Override
    @Cacheable(value = "orders",unless = "#result == null")
    public OrderDto getOrderById(Long orderId) throws OrderNotFoundException{
        Optional<Order> order=orderRepository.findById(orderId); // select * from orders where id=orderId
        if(!order.isPresent()) {
            throw new OrderNotFoundException("Order not found with id: " + orderId);
        }
        return orderConversion.toDto(order.get());

    }

    //@Scheduled(fixedRate = 60000)
    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
    @CacheEvict(value = "orders", allEntries = true)
    public void clearOrderCache() {
        System.out.println("Clearing order cache...");
    }


}
