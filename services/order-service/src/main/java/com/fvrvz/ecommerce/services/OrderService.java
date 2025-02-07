package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.records.OrderRequest;
import com.fvrvz.ecommerce.repositories.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Integer createOrder(@Valid OrderRequest request) {
        return null;
    }
}
