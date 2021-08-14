package com.awsDemo.shop.product.service;

import com.awsDemo.shop.product.domain.Product;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Service;

@Service
public class SqsReceiver {

    @SqsListener("order-queue")
    public void userCacheListener(Product product) {
        System.out.println("Product RECEIVED!!!");
    }

}
