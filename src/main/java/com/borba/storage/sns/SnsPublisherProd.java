package com.borba.storage.sns;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.borba.storage.configuration.AwsProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class SnsPublisherProd implements  SnsPublisher {

    private final String  topic;
    private final AmazonSNS snsClient;

    public SnsPublisherProd(AwsProperties props) {
        this.topic = props.getTopic();
        this.snsClient = AmazonSNSClientBuilder.standard()
                .withRegion(props.getRegion())
                .build();
    }
    @Override
    public void publishMessage(String message) {
        try {
            PublishRequest request = new PublishRequest()
                    .withTopicArn(topic)
                    .withMessage(message);

            PublishResult result = snsClient.publish(request);
            System.out.println("Mensagem publicada na aws com ID: " + result.getMessageId());

        } catch (Exception e) {
            System.err.println("Erro ao publicar no SNS: " + e.getMessage());
        }
    }
}

