package com.awsDemo.shop.product.controller;

import com.awsDemo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable String id) {
        return productService.getProductById(id).toString();
    }

    @PutMapping("product/{id}/add")
    public String updateProduct(@PathVariable String id) {
        return productService.updateProductById(id);
    }
}
