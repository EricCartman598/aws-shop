package com.awsDemo.shop.product.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.awsDemo.shop.product.domain.Product;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SqsSender {
    private QueueMessagingTemplate queueMessagingTemplate;

    public SqsSender(AmazonSQSAsync amazonSQSAsync) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSQSAsync);
    }

    public void send(Product product) {
        queueMessagingTemplate.convertAndSend("order-queue", product);
    }
}
