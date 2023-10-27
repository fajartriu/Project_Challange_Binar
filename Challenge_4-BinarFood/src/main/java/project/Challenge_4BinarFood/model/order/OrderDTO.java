package project.Challenge_4BinarFood.model.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class OrderDTO {
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
