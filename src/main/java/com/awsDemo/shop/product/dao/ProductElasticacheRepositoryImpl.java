package com.awsDemo.shop.product.dao;

import com.awsDemo.shop.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductElasticacheRepositoryImpl implements ProductRepository {
    private static final String CACHE_NAME = "awsshopelasticache";
    private RedisTemplate<String, Product> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public ProductElasticacheRepositoryImpl(RedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Product addProduct(Product product) {
        hashOperations.put(CACHE_NAME, product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return Optional.of((Product) hashOperations.get(CACHE_NAME, id));
    }
}