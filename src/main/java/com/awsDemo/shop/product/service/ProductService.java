package com.awsDemo.shop.product.service;

import com.awsDemo.shop.product.domain.Product;

public interface ProductService {
    Product getProductById(String id);

    String updateProductById(String id);
}
