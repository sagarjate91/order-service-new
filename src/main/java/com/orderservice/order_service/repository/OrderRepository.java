package com.orderservice.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.orderservice.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{



}
