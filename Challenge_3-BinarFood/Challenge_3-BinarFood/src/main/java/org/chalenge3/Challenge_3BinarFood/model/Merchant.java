package org.chalenge3.Challenge_3BinarFood.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Size(max = 50)
    @Column(name = "merchant_name")
    private String merchantName;
    @Size(max = 50)
    @Column(name = "merchant_location")
    private String merchantLocation;
    private boolean open;

    @OneToMany(mappedBy = "merchant")
    private List<Product> product;
}
