package project.Challenge_4BinarFood.model.merchant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStatusMerchantRequest {
    @JsonIgnore
    @NotBlank
    private UUID id;
    @Size(max = 50)
    private String merchantName;
    @Size(max = 50)
    private String merchantLocation;
    private boolean open;
}
