package project.Challenge_4BinarFood.model.users;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public class UpdateUserRequest {
    @Schema(
            description = " My descriptions",
            type = "array",
            example = " {\"myParam\" :\"value\" ,"
                    + "\"datacenter\": \"USA\"}")
    private Map<String, Object> request;
}
