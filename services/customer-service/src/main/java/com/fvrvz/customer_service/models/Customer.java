package com.fvrvz.customer_service.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@Document
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
