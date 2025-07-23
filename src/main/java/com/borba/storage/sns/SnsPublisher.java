package com.borba.storage.sns;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

public class SnsPublisher {

    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:430118847461:s3-operation-alerts";

    public static void publishMessage(String message){
        try (SnsClient snsClient = SnsClient.create()){

            PublishRequest publishRequest = PublishRequest.builder()
                    .topicArn(TOPIC_ARN)
                    .message(message)
                    .build();
            snsClient.publish(publishRequest);
            System.out.println("Mensagem publicada no sns");

        }
    }

}
