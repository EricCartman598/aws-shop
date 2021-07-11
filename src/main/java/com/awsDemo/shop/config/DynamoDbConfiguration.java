package com.awsDemo.shop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Configuration
public class DynamoDbConfiguration {

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(getAmazonDynamoDBClient());
    }

    private AmazonDynamoDB getAmazonDynamoDBClient() {
        String accessKey = getSecretValueByKey(getSecret("dynamo-db-credentials")
                .orElseThrow(RuntimeException::new), "accessKey");
        String secretKey = getSecretValueByKey(getSecret("dynamo-db-credentials")
                .orElseThrow(RuntimeException::new), "secretKey");


        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(
                                "dynamodb.eu-central-1.amazonaws.com",
                                "eu-central-1"
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

    private Optional<Secret> getSecret(String secretName) {
        KubernetesClient client = new DefaultKubernetesClient();
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        List<Secret> items = secrets.list().getItems();

        for (Secret secret : items) {
            if (secret.getMetadata().getName().equals(secretName)) {
                return Optional.of(secret);
            }
        }
        return Optional.empty();
    }

    private String getSecretValueByKey(Secret secret, String key) {
        String encodedValue = secret.getData().get(key);
        return new String(Base64.getDecoder().decode(encodedValue));
    }
}
