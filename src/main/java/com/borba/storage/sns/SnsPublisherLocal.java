package com.borba.storage.sns;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.borba.storage.configuration.AwsProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("local")
public class SnsPublisherLocal implements SnsPublisher {

    private final String topic;
    private final AmazonSNS snsClient;

    public SnsPublisherLocal(AwsProperties props) {
        this.topic = props.getTopic();
        this.snsClient = AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(props.getEndpoint(), props.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("test", "test")))
                .build();
    }

    @Override
    public void publishMessage(String message) {
        PublishResult result = snsClient.publish(new PublishRequest().withTopicArn(topic).withMessage(message));
        System.out.println("Mensagem publicada no localstack com ID: " + result.getMessageId());
    }
}

