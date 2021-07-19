package com.awsDemo.shop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.common.io.Files;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class DynamoDbConfiguration {
    private static final String K8S_SECRETS_PATH = "/etc/secret-volume";

    @Value("${db.url}")
    private String dbUrl;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(getAmazonDynamoDBClient());
    }

    private AmazonDynamoDB getAmazonDynamoDBClient() {
        String accessKey = getSecret("accessKey");
        String secretKey = getSecret("secretKey");

        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                dbUrl,
                                region
                        )
                )
                .withCredentials(
                        new AWSStaticCredentialsProvider(
                                new BasicAWSCredentials(
                                        accessKey,
                                        secretKey
                                )
                        )
                )
                .build();
    }

    private String getSecret(String secretName) {
        String secret = Strings.EMPTY;
        File accessKeyFile = new File(K8S_SECRETS_PATH + "/" + secretName);
        try {
            secret = Files.readLines(accessKeyFile, StandardCharsets.UTF_8).get(0);
        } catch (IOException e) {
            new RuntimeException("No secret has been found!");
        }
        return secret;
    }
}
