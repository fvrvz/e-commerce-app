package com.fvrvz.ecommerce.services;

import com.fvrvz.ecommerce.exceptions.ProductPurchaseException;
import com.fvrvz.ecommerce.mappers.ProductMapper;
import com.fvrvz.ecommerce.records.ProductPurchaseRequest;
import com.fvrvz.ecommerce.records.ProductPurchaseResponse;
import com.fvrvz.ecommerce.records.ProductRequest;
import com.fvrvz.ecommerce.records.ProductResponse;
import com.fvrvz.ecommerce.repositories.ProductRepository;
import com.fvrvz.ecommerce.utils.Merge;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class ProductService {
    @Autowired
    private ProductRepository _productRepository;

    @Autowired
    private ProductMapper _productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = this._productMapper.toProduct(request);
        return this._productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var productIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = this._productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exists");
        }

        var storedRequest = request
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException(format("Insufficient stock quantity for the product with Id: %s", productRequest.productId()));
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            this._productRepository.save(product);
            purchasedProducts.add(this._productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return this._productRepository.findById(productId)
                .map(this._productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException(format("Product not found with ID: %s", productId)));
    }

    public List<ProductResponse> findAll() {
        return this._productRepository.findAll()
                .stream().map(this._productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public void deleteProductById(Integer productId) {
        this._productRepository.deleteById(productId);
    }

    public void updateProduct(ProductRequest request) {
        var product = this._productRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(format("Product not found with ID: %s", request.id())));
        Merge.mergeProduct(product, request);
        this._productRepository.save(product);
    }
}
