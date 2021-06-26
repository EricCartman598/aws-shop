package com.awsDemo.shop.product.controller;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.awsDemo.shop.product.domain.Product;
import com.awsDemo.shop.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@XRayEnabled
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getGreeting() {
        logger.debug("access to main page");

        return "<h2>Hello from Spring boot. <br/><p style=\"color:green\">This is the latest version</p></h2>";
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable String id) {
        logger.debug("get product with id = " + id);

        return productService.getProductById(id).toString();
    }

    @PutMapping("product/add")
    public String updateProduct(@RequestBody Product product) {
        logger.debug("add new product: " + product.toString());

        return productService.addProduct(product);
    }
}
