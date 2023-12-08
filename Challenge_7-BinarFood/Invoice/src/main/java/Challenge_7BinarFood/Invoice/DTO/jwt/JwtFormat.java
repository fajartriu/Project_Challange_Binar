package Challenge_7BinarFood.Invoice.DTO.jwt;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@Builder
public class JwtFormat {
    private String algoritma;
    private Map<String, String> payload;
    private String signature;
}
