package Challenge_7BinarFood.Invoice.repository.user;


import Challenge_7BinarFood.Invoice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
//    Optional<User> findByEmail(String email);
}
