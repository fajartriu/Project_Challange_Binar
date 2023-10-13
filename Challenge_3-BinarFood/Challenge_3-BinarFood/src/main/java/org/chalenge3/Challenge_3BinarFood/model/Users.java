package org.chalenge3.Challenge_3BinarFood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Max(50)
    private String username;
    @Max(50)
    private String email_address;
    @Max(50)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Order> order;

    public Users(String username, String email_address, String password) {
        this.username = username;
        this.email_address = email_address;
        this.password = password;
    }
}
