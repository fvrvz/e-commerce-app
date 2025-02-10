package com.fvrvz.ecommerce.records;

import com.fvrvz.ecommerce.models.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
