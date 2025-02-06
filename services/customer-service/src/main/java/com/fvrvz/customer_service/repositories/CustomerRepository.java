package com.fvrvz.customer_service.repositories;

import com.fvrvz.customer_service.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
