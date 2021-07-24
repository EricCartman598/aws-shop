package com.awsDemo.shop.product.dao;

import com.awsDemo.shop.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository {
    Product addProduct(Product product);
    Product getProductById(String id);
}