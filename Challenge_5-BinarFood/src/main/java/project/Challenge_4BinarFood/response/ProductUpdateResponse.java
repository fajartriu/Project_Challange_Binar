package project.Challenge_4BinarFood.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateResponse {
    private UUID id;
    private UUID merchantId;
    private String productName;
    private Double price;
    private boolean deleteProduct;
}
