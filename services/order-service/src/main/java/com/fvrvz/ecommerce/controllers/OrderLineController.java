package com.fvrvz.ecommerce.controllers;

import com.fvrvz.ecommerce.records.OrderLineResponse;
import com.fvrvz.ecommerce.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-line")
public class OrderLineController {
    @Autowired
    private OrderLineService _orderLineService;

    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(this._orderLineService.findAllByOrderId(orderId));
    }
}
