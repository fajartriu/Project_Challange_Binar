package com.example.demo_kafka.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics="BinarFoodChat", groupId = "binar-group-id")
    public void sendMessage(String topic, String message){
        System.out.println("Pesan diterima di topic"+ topic+": "+message);
    }
}
