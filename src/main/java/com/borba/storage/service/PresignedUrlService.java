package com.borba.storage.service;

import com.borba.storage.configuration.AwsProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

@Service
public class PresignedUrlService {

    private final S3Presigner s3Presigner;
    private final AwsProperties awsProperties;

    public PresignedUrlService(S3Presigner s3Presigner, AwsProperties awsProperties) {
        this.s3Presigner = s3Presigner;
        this.awsProperties = awsProperties;
    }

    public String gerarPresignedUrl(String key, Duration validade) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(awsProperties.getBucket())
                .key(key)
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(validade)
                .getObjectRequest(getObjectRequest)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(presignRequest);

        return presignedRequest.url().toString();
    }
}
