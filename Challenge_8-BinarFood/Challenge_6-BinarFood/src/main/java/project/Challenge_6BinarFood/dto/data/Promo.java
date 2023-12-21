package project.Challenge_6BinarFood.dto.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@Data
public class Promo {
    private UUID produductId;
    private String merchantName;
    private String productName;
    private Double price;
    private Integer percent;
    private Double priceDiscount;
}
