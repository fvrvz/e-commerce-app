package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.mappers.OrderLineMapper;
import com.fvrvz.ecommerce.records.OrderLineRequest;
import com.fvrvz.ecommerce.repositories.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderLineService {
    @Autowired
    private OrderLineRepository _orderLineRepository;

    @Autowired
    private OrderLineMapper _orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var orderLine = this._orderLineMapper.toOrderLine(request);
        return this._orderLineRepository.save(orderLine).getId();
    }
}
