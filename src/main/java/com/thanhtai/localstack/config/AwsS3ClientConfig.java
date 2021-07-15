package com.thanhtai.localstack.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class AwsS3ClientConfig {

    @Value("${aws.s3.endpoint}")
    private String awsS3Endpoint;
    @Value("${aws.s3.region}")
    private String awsS3Region;
    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;

    AWSCredentials awsCredentials = new BasicAWSCredentials("test", "test");

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsS3Endpoint, awsS3Region))
                .withPathStyleAccessEnabled(true)
                .build();
    }

    public String getAwsS3Bucket() {
        return awsS3Bucket;
    }
}
