package com.aws.s3.example.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfiguration {
    private static final String ACCESS_KEY = "your key";
    private static final String SECRET_KEY = "your secret key";
    private static final String REGION = "region";

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return AmazonS3ClientBuilder.standard().withRegion(REGION)
                                    .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials())).build();
    }
}