package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.clients.CustomerClient;
import com.fvrvz.ecommerce.clients.ProductClient;
import com.fvrvz.ecommerce.exceptions.BusinessException;
import com.fvrvz.ecommerce.mappers.OrderMapper;
import com.fvrvz.ecommerce.records.OrderLineRequest;
import com.fvrvz.ecommerce.records.OrderRequest;
import com.fvrvz.ecommerce.records.PurchaseRequest;
import com.fvrvz.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository _orderRepository;

    @Autowired
    private CustomerClient _customerClient;

    @Autowired
    private ProductClient _productClient;

    @Autowired
    private OrderMapper _orderMapper;

    @Autowired
    private OrderLineService _orderLineService;

    public Integer createOrder(OrderRequest request) {
        var customer = this._customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order. No customer exist with ID: " + request.customerId()));

        this._productClient.purchaseProducts(request.products());

        var order = this._orderRepository.save(_orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            this._orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        // todo start payment process

        // send the order confirmation --> notification service using kafka

        return null;
    }
}
