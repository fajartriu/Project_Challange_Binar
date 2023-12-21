package com.example.demo_kafka.service;

import com.example.demo_kafka.dto.MessageRequest;
import com.example.demo_kafka.dto.MessageResponse;
import com.example.demo_kafka.kafka.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageProducer messageProducer;
    public MessageResponse send(MessageRequest messageRequest){
        System.out.println(messageRequest.getMessage());
        messageProducer.sendMessage("BinarFoodChat", messageRequest.getMessage());

        return MessageResponse.builder()
                .message(messageRequest.getMessage())
//                .sendTime(LocalDateTime.now())
                .build();
    }
}
