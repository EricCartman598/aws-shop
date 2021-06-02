package com.awsDemo.shop.product.dao;

import com.awsDemo.shop.product.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product getById(Long id) {
        return new Product(id, "Milk", 50L);
    }

    @Override
    public void updateById(Long id) {

    }
}
