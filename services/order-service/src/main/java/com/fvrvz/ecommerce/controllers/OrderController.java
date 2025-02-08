package com.fvrvz.ecommerce.controllers;

import com.fvrvz.ecommerce.records.OrderRequest;
import com.fvrvz.ecommerce.records.OrderResponse;
import com.fvrvz.ecommerce.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService _orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok(this._orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(this._orderService.findAll());
    }

    @GetMapping("/{order-id")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(this._orderService.findById(orderId));
    }
}
