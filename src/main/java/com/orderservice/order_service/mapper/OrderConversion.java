package com.orderservice.order_service.mapper;

import org.mapstruct.Mapper;

import com.orderservice.order_service.dto.OrderDto;
import com.orderservice.order_service.model.Order;

@Mapper(componentModel = "spring")
public interface OrderConversion {

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);


}
