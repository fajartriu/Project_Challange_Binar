package project.Challenge_4BinarFood.model.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
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
public class CreateUsersRequest {
    @Size(max = 50)
    @NotBlank
    private String userName;
    @Size(max = 50)
    @Email
    @NotBlank
    private String emailAddress;
    @Size(max = 50)
    @NotBlank
    private String password;
}
