package com.fvrvz.ecommerce.repositories;

import com.fvrvz.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
