package project.Challenge_6BinarFood.dto.request.fcm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationRequest {
    private String title;
    private String body;
    private String topic;
    private String token;
}
