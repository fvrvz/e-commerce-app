package com.fvrvz.ecommerce.utils;

import com.fvrvz.ecommerce.entities.Category;
import com.fvrvz.ecommerce.entities.Product;
import com.fvrvz.ecommerce.records.ProductRequest;
import org.apache.commons.lang.StringUtils;

public class Merge {
    public static void mergeProduct(Product product, ProductRequest request) {
        if (StringUtils.isNotBlank(request.name())) {
            product.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.description())) {
            product.setDescription(request.description());
        }
        product.setAvailableQuantity(request.availableQuantity());
        product.setPrice(request.price());
        product.setCategory(Category.builder().id(request.categoryId()).build());
    }
}
