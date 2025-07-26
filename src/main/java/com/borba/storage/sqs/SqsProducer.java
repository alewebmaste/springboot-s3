package com.borba.storage.sqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsProducer {

    @Autowired
    private SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public void sendMessage(String messageBoby){
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBoby)
                .build();
        sqsClient.sendMessage(request);
    }

}
