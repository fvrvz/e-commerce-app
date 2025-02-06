package com.fvrvz.customer_service.records;

import com.fvrvz.customer_service.models.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
