package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.exceptions.CustomerNotFoundException;
import com.fvrvz.ecommerce.mappers.CustomerMapper;
import com.fvrvz.ecommerce.records.CustomerRequest;
import com.fvrvz.ecommerce.records.CustomerResponse;
import com.fvrvz.ecommerce.repositories.CustomerRepository;
import com.fvrvz.ecommerce.utils.Merger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository _customerRepository;

    @Autowired
    private CustomerMapper _customerMapper;

    public String createCustomer(CustomerRequest request) {
        var customer = this._customerRepository.save(this._customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = this._customerRepository
                .findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer found: %s", request.id())
                ));
        Merger.mergeCustomer(customer, request);
        this._customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return this._customerRepository
                .findAll()
                .stream()
                .map(this._customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findCustomerById(String customerId) {
        return this._customerRepository
                .findById(customerId)
                .map(this._customerMapper::fromCustomer)
                .orElseThrow(() ->
                        new CustomerNotFoundException(format("Customer not found: %s", customerId))
                );
    }

    public void deleteCustomer(String customerId) {
        this._customerRepository.deleteById(customerId);
    }
}
