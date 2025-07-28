package com.borba.storage.sqs;

import com.borba.storage.configuration.AwsProperties;
import com.borba.storage.service.PresignedUrlService;
import com.borba.storage.sns.SnsPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.time.Duration;
import java.util.List;

@Service
public class SqsConsumer {

    private final  SqsClient sqsClient;
    private final String queueUrl;
    private final PresignedUrlService presignedUrlService;
    private final SnsPublisher snsPublisher;

    public SqsConsumer(SqsClient sqsClient, AwsProperties props,PresignedUrlService presignedUrlService,
                       SnsPublisher snsPublisher) {
        this.sqsClient = sqsClient;
        this.queueUrl = props.getSqs().getQueueUrl();
        this.presignedUrlService = presignedUrlService;
        this.snsPublisher = snsPublisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void pullMessages(){

        //System.out.println("Verificando fila...");

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

                String body = msg.body();

                System.out.println("Recebida: " + msg.body());

                // Extrair a chave do arquivo da mensagem
                String key = extractKey(body);
                if (key == null || key.isBlank()) {
                    System.err.println("Mensagem inválida: chave não encontrada.");
                    continue;
                }

                // Gerar URL válida por 15 minutos
                String url = presignedUrlService.gerarPresignedUrl(key, Duration.ofMinutes(15));

                String mensagem = "Seu arquivo está pronto para download: " + url;
                snsPublisher.publishMessage(mensagem);

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

    private String extractKey(String body) {
        // Exemplo simples: "Arquivo enviado com sucesso. Key: nome-do-arquivo.txt"
        if (body.contains("Key:")) {
            return body.substring(body.indexOf("Key:") + 5).trim();
        }
        return null;
    }
}
