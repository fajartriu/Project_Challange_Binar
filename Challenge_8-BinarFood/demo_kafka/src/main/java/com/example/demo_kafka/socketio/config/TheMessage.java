package com.example.demo_kafka.socketio.config;

import lombok.Data;

@Data
public class TheMessage {
    private String from;
    private String to;
    private String message;
}