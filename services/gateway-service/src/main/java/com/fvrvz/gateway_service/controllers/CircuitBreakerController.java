package com.fvrvz.gateway_service.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class CircuitBreakerController {

    @GetMapping("/customer-service")
    public ResponseEntity<String> customerServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Customer service is not available at the moment. Please try after sometime.");
    }

    @GetMapping("/order-service")
    public ResponseEntity<String> orderServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Order service is not available at the moment. Please try after sometime.");
    }

    @GetMapping("/product-service")
    public ResponseEntity<String> productServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Product service is not available at the moment. Please try after sometime.");
    }

    @GetMapping("/payment-service")
    public ResponseEntity<String> paymentServiceFallback() {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Payment service is not available at the moment. Please try after sometime.");
    }
}
