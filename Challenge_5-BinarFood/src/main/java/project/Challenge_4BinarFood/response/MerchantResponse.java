package project.Challenge_4BinarFood.response;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MerchantResponse {
    private UUID id;
    private String merchantName;
    private String merchantLocation;
    private boolean open;
}
