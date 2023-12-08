package Challenge_7BinarFood.Invoice.DTO.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Payload {
    private String username;
    private long issue;
    private long expire;
}
