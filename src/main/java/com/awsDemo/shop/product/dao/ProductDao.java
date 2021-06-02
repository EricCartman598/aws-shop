package com.awsDemo.shop.product.dao;

import com.awsDemo.shop.product.domain.Product;

public interface ProductDao {
    Product getById(Long id);
    void updateById(Long id);
}
