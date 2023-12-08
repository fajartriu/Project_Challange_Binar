package com.example.demo_kafka.controller;

import com.example.demo_kafka.dto.MessageRequest;
import com.example.demo_kafka.dto.MessageResponse;
import com.example.demo_kafka.kafka.MessageProducer;
import com.example.demo_kafka.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/binarfood-message")
public class MessageController {
//    private final MessageService messageService;
    private final MessageProducer messageProducer;

//    @PostMapping(path = "send")
//    public MessageResponse sendMessage(@RequestBody MessageRequest messageRequest){
//        System.out.println(messageRequest.getMessage());
////        return messageService.send(messageRequest);
//    }

    @PostMapping(path = "send")
    public String sendMessage(@RequestParam("message") String message){
        messageProducer.sendMessage("BinarFoodChat", message);
        return "Pesan terkirim";
    }
}