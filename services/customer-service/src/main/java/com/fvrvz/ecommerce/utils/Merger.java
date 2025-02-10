package com.fvrvz.ecommerce.utils;

import com.fvrvz.ecommerce.models.Customer;
import com.fvrvz.ecommerce.records.CustomerRequest;
import org.apache.commons.lang.StringUtils;

public class Merger {
    public static void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }
}
