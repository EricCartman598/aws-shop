package com.awsDemo.shop.product.controller;

import com.awsDemo.shop.product.domain.Product;
import com.awsDemo.shop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getGreeting() {
        return "<h2>Hello from Spring boot. <br/>This is the latest version</h2>";
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable String id) {
        return productService.getProductById(id).toString();
    }

    @PutMapping("product/add")
    public String updateProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
