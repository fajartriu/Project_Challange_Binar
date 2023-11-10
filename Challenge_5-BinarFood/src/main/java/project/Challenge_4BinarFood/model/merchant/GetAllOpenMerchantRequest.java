package project.Challenge_4BinarFood.model.merchant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GetAllOpenMerchantRequest {
    private boolean open;
}
