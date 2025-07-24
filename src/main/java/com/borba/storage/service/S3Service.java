package com.borba.storage.service;

import com.borba.storage.configuration.AwsProperties;
import com.borba.storage.sns.SnsPublisher;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.List;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String  bucketName;
    private final SnsPublisher snsPublisher;

    public S3Service(AwsProperties props, SnsPublisher snsPublisher){

        this.bucketName = props.getBucket();
        this.snsPublisher = snsPublisher;

        this.s3Client = S3Client.builder()
                .region(Region.of(props.getRegion()))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public void uploadFile(String key, byte[] content){
        s3Client.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                RequestBody.fromBytes(content));
        snsPublisher.publishMessage("Arquivo enviado para o bucket com sucesso!");
    }

    public byte[] downloadFile(String key){

        ResponseBytes<GetObjectResponse> response = s3Client
                .getObjectAsBytes(GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build()
                );

        return response.asByteArray();
    }

    public List<String> listFiles(){
        return s3Client.listObjectsV2(ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build())
                .contents()
                .stream()
                .map(S3Object::key)
                .toList();
    }

    public void deleteFiles(String key){
        s3Client.deleteObject(DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                .build());
    }


}
