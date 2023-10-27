package org.chalenge3.Challenge_3BinarFood.repository;

import org.chalenge3.Challenge_3BinarFood.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
}
