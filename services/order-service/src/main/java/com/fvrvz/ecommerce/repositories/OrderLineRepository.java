package com.fvrvz.ecommerce.repositories;

import com.fvrvz.ecommerce.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
