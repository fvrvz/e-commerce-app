package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.exceptions.CustomerNotFoundException;
import com.fvrvz.ecommerce.mappers.CustomerMapper;
import com.fvrvz.ecommerce.models.Customer;
import com.fvrvz.ecommerce.records.CustomerRequest;
import com.fvrvz.ecommerce.records.CustomerResponse;
import com.fvrvz.ecommerce.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.apache.commons.lang.StringUtils;
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

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = this._customerRepository
                .findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer found: %s", request.id())
                ));
        mergeCustomer(customer, request);
        this._customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
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
