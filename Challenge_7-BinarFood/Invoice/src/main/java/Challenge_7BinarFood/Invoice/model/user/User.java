package Challenge_7BinarFood.Invoice.model.user;

import Challenge_7BinarFood.Invoice.model.merchant.Merchant;
import Challenge_7BinarFood.Invoice.model.order.Order;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private boolean active;
    private String otp;
    private LocalDateTime otpGeneratedTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Order> order;

    @OneToMany(mappedBy = "user")
    private List<Merchant> merchants;
}
