package com.fvrvz.ecommerce.records;

public record Customer(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
