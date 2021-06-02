package com.awsDemo.shop.product.service;

import com.awsDemo.shop.product.dao.ProductRepository;
import com.awsDemo.shop.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(Long.parseLong(id)).get();
    }

    @Override
    public String addProduct(Product product) {
        if(productRepository.save(product) != null) {
            return "New product has been added to database";
        }
        else {
            return "Error while adding a new product to database!";
        }
    }
}
