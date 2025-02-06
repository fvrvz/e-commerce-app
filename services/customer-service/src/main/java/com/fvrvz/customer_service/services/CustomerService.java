package com.fvrvz.customer_service.services;

import com.fvrvz.customer_service.exceptions.CustomerNotFoundException;
import com.fvrvz.customer_service.mappers.CustomerMapper;
import com.fvrvz.customer_service.models.Customer;
import com.fvrvz.customer_service.records.CustomerRequest;
import com.fvrvz.customer_service.records.CustomerResponse;
import com.fvrvz.customer_service.repositories.CustomerRepository;
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
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = this.customerRepository
                .findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer found: %s", request.id())
                ));
        mergeCustomer(customer, request);
        this.customerRepository.save(customer);
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
        List<CustomerResponse> customers = this.customerRepository
                .findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
        return customers;
    }

    public CustomerResponse findCustomerById(String customerId) {
        return this.customerRepository
                .findById(customerId)
                .map(this.customerMapper::fromCustomer)
                .orElseThrow(() ->
                        new CustomerNotFoundException(format("Customer not found: %s", customerId))
                );
    }

    public void deleteCustomer(String customerId) {
        this.customerRepository.deleteById(customerId);
    }
}
