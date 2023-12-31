package org.chalenge3.Challenge_3BinarFood.repository;

import org.chalenge3.Challenge_3BinarFood.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
}
