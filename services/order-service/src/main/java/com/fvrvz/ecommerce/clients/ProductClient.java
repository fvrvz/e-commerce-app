package com.fvrvz.ecommerce.clients;

import com.fvrvz.ecommerce.records.PurchaseRequest;
import com.fvrvz.ecommerce.records.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "products-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {
    @PostMapping("/purchase")
    Optional<List<PurchaseResponse>> purchaseProducts(
            @RequestBody List<PurchaseRequest> requests
    );
}
