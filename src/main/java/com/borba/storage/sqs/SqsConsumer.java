package com.borba.storage.sqs;

import com.borba.storage.configuration.AwsProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
public class SqsConsumer {

    private SqsClient sqsClient;
    private String queueUrl;

    public SqsConsumer(SqsClient sqsClient, AwsProperties props) {
        this.sqsClient = sqsClient;
        this.queueUrl = props.getSqs().getQueueUrl();
    }

    @Scheduled(fixedDelay = 5000)
    public void pullMessages(){

        System.out.println("Verificando fila...");

        List<Message> messages = sqsClient.receiveMessage(
                ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .maxNumberOfMessages(5)
                        .waitTimeSeconds(10)
                        .build()
        ).messages();

        for (Message msg : messages){

            try {
                //Processar mensagem
                System.out.println("Recebida: " + msg.body());

                sqsClient.deleteMessage(DeleteMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .receiptHandle(msg.receiptHandle())
                        .build());
            }catch (Exception ex){
                System.err.println("Erro " + ex.getMessage());
                //Mensagem não deletada vai pra fila
            }

        }
    }
}
