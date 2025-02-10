package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.clients.CustomerClient;
import com.fvrvz.ecommerce.clients.PaymentClient;
import com.fvrvz.ecommerce.clients.ProductClient;
import com.fvrvz.ecommerce.exceptions.BusinessException;
import com.fvrvz.ecommerce.kafka.OrderProducer;
import com.fvrvz.ecommerce.mappers.OrderMapper;
import com.fvrvz.ecommerce.records.*;
import com.fvrvz.ecommerce.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private OrderProducer _orderProducer;

    @Autowired
    private PaymentClient _paymentClient;

    public Integer createOrder(OrderRequest request) {
        var customer = this._customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order. No customer exist with ID: " + request.customerId()));

        var purchasedProducts = this._productClient.purchaseProducts(request.products()).orElseThrow(() ->
                new BusinessException("Cannot fetch purchased products for customer ID: " + request.customerId())
        );

        var order = this._orderRepository.save(_orderMapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            this._orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        this._paymentClient.createPayment(paymentRequest);

        this._orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return this._orderRepository.findAll()
                .stream()
                .map(this._orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return this._orderRepository.findById(orderId).map(this._orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with ID: %d", orderId)));
    }
}
