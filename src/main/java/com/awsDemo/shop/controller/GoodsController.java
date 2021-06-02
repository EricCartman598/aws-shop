package com.awsDemo.shop.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {
    @GetMapping("goods")
    public String getProduct() {
        return "product";
    }
}
