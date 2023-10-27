package project.Challenge_4BinarFood.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private LocalDateTime orderTime;
    private String username;
    private String productName;
    private String merchantName;
    private String destinationAddress;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private boolean completed;
}
