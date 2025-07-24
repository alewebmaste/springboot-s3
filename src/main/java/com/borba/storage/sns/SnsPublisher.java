package com.borba.storage.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.borba.storage.configuration.AwsProperties;
import org.springframework.stereotype.Service;

@Service
public class SnsPublisher {

    private final String  topic;
    private final AmazonSNS snsClient;

    public SnsPublisher(AwsProperties props) {
        this.topic = props.getTopic();
        this.snsClient = AmazonSNSClientBuilder.standard()
                .withRegion(props.getRegion())
                .build();
    }

    public void publishMessage(String message) {
        try {
            PublishRequest request = new PublishRequest()
                    .withTopicArn(topic)
                    .withMessage(message);

            PublishResult result = snsClient.publish(request);
            System.out.println("Mensagem publicada com ID: " + result.getMessageId());

        } catch (Exception e) {
            System.err.println("Erro ao publicar no SNS: " + e.getMessage());
        }
    }
}

