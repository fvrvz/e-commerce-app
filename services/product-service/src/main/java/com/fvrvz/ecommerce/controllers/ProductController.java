package com.fvrvz.ecommerce.controllers;

import com.fvrvz.ecommerce.records.ProductPurchaseRequest;
import com.fvrvz.ecommerce.records.ProductPurchaseResponse;
import com.fvrvz.ecommerce.records.ProductRequest;
import com.fvrvz.ecommerce.records.ProductResponse;
import com.fvrvz.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired
    private ProductService _productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(this._productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(this._productService.purchaseProducts(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.accepted().body(null);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable("product-id") String productId) {
        return ResponseEntity.ok(this._productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(this._productService.findAll());
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable("product-id") String productId) {
        return ResponseEntity.accepted().body(null);
    }
}
