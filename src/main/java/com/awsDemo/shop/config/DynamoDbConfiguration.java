package com.awsDemo.shop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.google.common.io.Files;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamoDbConfiguration {
    private static final String K8S_SECRETS_PATH = "/etc/secret-volume";
    private static final String DYNAMO_DB_CREDENTIALS = "dynamo-db-credentials";

    @Value("${db.url}")
    private String dbUrl;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(getAmazonDynamoDBClient());
    }

    private AmazonDynamoDB getAmazonDynamoDBClient() {
//        String accessKey = getK8sSecret("accessKey");
//        String secretKey = getK8sSecret("secretKey");
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

    private String getK8sSecret(String secretName) {
        String secret = Strings.EMPTY;
        File accessKeyFile = new File(K8S_SECRETS_PATH + "/" + secretName);
        try {
            secret = Files.readLines(accessKeyFile, StandardCharsets.UTF_8).get(0);
        } catch (IOException e) {
            new RuntimeException("No secret has been found!");
        }
        return secret;
    }

    // Use this code snippet in your app.
    // If you need more information about configurations or implementing the sample code, visit the AWS docs:
    // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-samples.html#prerequisites
    public String getSecret(String secretName) {
        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        // We rethrow the exception by default.
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(DYNAMO_DB_CREDENTIALS);
        GetSecretValueResult getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        String secret = getSecretValueResult.getSecretString();
        Map<String, String> secretMap = new HashMap<>();
        for(String s : secret.substring(1, secret.length() - 1).split(",")) {
            String[] split = s.split(":");
            secretMap.put(split[0].substring(1, split[0].length() - 1), split[1].substring(1, split[1].length() - 1));
        }
        return secretMap.get(secretName);
    }
}
