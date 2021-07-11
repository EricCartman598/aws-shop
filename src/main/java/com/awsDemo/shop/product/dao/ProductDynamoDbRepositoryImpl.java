package com.awsDemo.shop.product.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.awsDemo.shop.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public class ProductDynamoDbRepositoryImpl implements ProductRepository {
    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Product addProduct(Product product) {
        dynamoDBMapper.save(product);
        return product;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        System.out.println("getting from DB");
        return Optional.of(dynamoDBMapper.load(Product.class, id));
    }
}
