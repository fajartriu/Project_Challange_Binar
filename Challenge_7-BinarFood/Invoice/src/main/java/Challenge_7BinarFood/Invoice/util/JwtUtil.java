package Challenge_7BinarFood.Invoice.util;

import Challenge_7BinarFood.Invoice.DTO.jwt.JwtFormat;
import org.json.JSONException;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    public String getToken(String token){
        if (token != null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return token;
    }
    public JwtFormat decodeToken(String token) throws JSONException {
        if (!token.startsWith("Bearer ")){
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length == 3){
                String header = tokenParts[0];
                String payload = tokenParts[1];
                String signature = tokenParts[2];

                String decodeHeader = decodeBase64(header);
                String decodePayload = decodeBase64(payload);

                // Parsing the decoded payload as JSON
                JSONObject jsonHeader = new JSONObject(decodeHeader);
                JSONObject jsonPayload = new JSONObject(decodePayload);

                String alg = jsonHeader.getString("alg");
                String subject = jsonPayload.getString("sub");
                String issueAt = jsonPayload.getString("iat");
                String expire = jsonPayload.getString("exp");

                Map<String, String> payloadMap = new HashMap<>();
                payloadMap.put("username", subject);
                payloadMap.put("issue", issueAt);
                payloadMap.put("expire", expire);


                return JwtFormat.builder()
                        .algoritma(alg)
                        .payload(payloadMap)
                        .signature(signature)
                        .build();
            }else {
                System.out.println("Invalid JWT Format");
            }
        }
        return null;
    }

    public static String decodeBase64(String input) {
        byte[] decodedBytes = Base64.getUrlDecoder().decode(input);
        return new String(decodedBytes);
    }
}
