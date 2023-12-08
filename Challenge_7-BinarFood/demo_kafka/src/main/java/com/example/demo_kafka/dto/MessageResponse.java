package com.example.demo_kafka.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class MessageResponse {
    private String message;
//    private LocalDateTime sendTime;
}
