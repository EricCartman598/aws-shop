package com.awsDemo.shop.product.controller;

import com.awsDemo.shop.product.domain.Product;
import com.awsDemo.shop.product.service.ProductService;
import com.awsDemo.shop.product.service.SqsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    @Autowired
    SqsSender sqsSender;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getGreeting() {
        LOGGER.debug("access to main page");

        return "<h2>Hello from Spring boot. <br/><p style=\"color:green\">This app works within ECS cluster</p></h2>";
    }

    @GetMapping("product/{id}")
    public String getProduct(@PathVariable String id) {
        LOGGER.debug("get product with id = " + id);

        return productService.getProductById(id).toString();
    }

    @PutMapping("product/add")
    public String updateProduct(@RequestBody Product product) {
        LOGGER.debug("add new product: " + product.toString());

        return productService.addProduct(product);
    }

    @GetMapping("product/addToCart/{id}")
    public void addProductToCart(@PathVariable String id) {
        Product product = new Product();
        product.setId("1");
        product.setName("FUCK");
        product.setPrice("150");
        sqsSender.send(product);
    }
}
