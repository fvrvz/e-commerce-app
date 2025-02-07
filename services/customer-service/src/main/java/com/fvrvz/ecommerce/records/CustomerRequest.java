package com.fvrvz.ecommerce.records;

import com.fvrvz.ecommerce.models.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer's firstname is required!")
        String firstName,
        @NotNull(message = "Customer's lastname is required!")
        String lastName,
        @NotNull(message = "Customer's email is required!")
        @Email(message = "Not a valid email address!")
        String email,
        Address address
) {
}
