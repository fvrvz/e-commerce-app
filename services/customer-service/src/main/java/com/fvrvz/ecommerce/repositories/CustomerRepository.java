package com.fvrvz.ecommerce.repositories;

import com.fvrvz.ecommerce.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
