package org.example;

import org.example.service.AppService;

public class Main {
    public static void main(String[] args) {
        AppService as = new AppService();
        as.initiateData();
        as.run();
    }
}