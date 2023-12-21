package com.example.demo_kafka.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class MessageRequest {
    private String message;
}
