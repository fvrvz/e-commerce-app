package com.fvrvz.ecommerce.records;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
