package com.fvrvz.ecommerce.clients;

import com.fvrvz.ecommerce.records.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@FeignClient(name = "PAYMENT-SERVICE", path = "/api/v1/payments")
public interface PaymentClient {
    @PostMapping
    Optional<ResponseEntity<Integer>> createPayment(PaymentRequest request);
}
