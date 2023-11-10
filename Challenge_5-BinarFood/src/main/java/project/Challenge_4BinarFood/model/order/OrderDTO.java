package project.Challenge_4BinarFood.model.order;

import jakarta.validation.constraints.Size;
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
    @Size(max = 50)
    private String username;
    @Size(max = 50)
    private String productName;
    @Size(max = 50)
    private String merchantName;
    @Size(max = 100)
    private String destinationAddress;
    private Integer quantity;
    private Double price;
    private Double totalPrice;
    private boolean completed;

}
