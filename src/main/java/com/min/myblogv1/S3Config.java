package com.min.myblogv1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.InstanceProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {


    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;


    @Bean
    public AwsCredentials basicAwsCredentials(){
        return AwsBasicCredentials.create(accessKey,secretKey);
    }

    @Bean
    public S3Client s3Client(AwsCredentials awsCredentials){
        return S3Client.builder()
                .region(Region.of("ap-northeast-2"))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();
    }
}