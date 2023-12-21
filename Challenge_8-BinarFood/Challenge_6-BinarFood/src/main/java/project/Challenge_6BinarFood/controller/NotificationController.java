package project.Challenge_6BinarFood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.Challenge_6BinarFood.dto.request.fcm.NotificationRequest;
import project.Challenge_6BinarFood.dto.response.fcm.NotificationResponse;
import project.Challenge_6BinarFood.service.fcm.FCMService;

import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {
    @Autowired
    private FCMService fcmService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new NotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

}
