package com.fvrvz.customer_service.mappers;

import com.fvrvz.customer_service.models.Customer;
import com.fvrvz.customer_service.records.CustomerRequest;
import com.fvrvz.customer_service.records.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) return null;
        return Customer
                .builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
