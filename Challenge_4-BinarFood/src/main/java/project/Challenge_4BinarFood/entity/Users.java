package project.Challenge_4BinarFood.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(max = 50)
    private String username;
    @Size(max = 50)
    @Column(name = "email_address")
    private String emailAddress;
    @Size(max = 50)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> order;

    public Users(String username, String emailAddress, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
