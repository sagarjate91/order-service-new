package com.orderservice.order_service.client;


import com.orderservice.order_service.dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PAYMENT-SERVICE", path = "/api/payments")
public interface PaymentClient {

    @PostMapping("/doPayment")
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto);
}
