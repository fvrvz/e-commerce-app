package com.fvrvz.ecommerce.repositories;

import com.fvrvz.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}
