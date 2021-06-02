package com.awsDemo.shop.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("product/{productId}")
    public String getProduct(@PathVariable String productId) {
        return "product " + productId;
    }

    @PutMapping("product/{id}/add")
    public String updateProduct() {
        return "product updated";
    }
}
