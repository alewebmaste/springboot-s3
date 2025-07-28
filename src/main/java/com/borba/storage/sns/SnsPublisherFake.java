package com.borba.storage.sns;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class SnsPublisherFake implements SnsPublisher {
    @Override
    public void publishMessage(String message) {
        System.out.println("Mensagem simulada: " + message);
    }
}

