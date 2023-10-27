package project.Challenge_4BinarFood.respository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.Challenge_4BinarFood.entity.Users;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL create_users(:id, :username, :emailAdress, :password);", nativeQuery = true)
    void createUsers(@Param("id") UUID id, @Param("username") String username, @Param("emailAdress") String emailAdress, @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL delete_user(:id);", nativeQuery = true)
    void deleteUsers(@Param("id") UUID id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "CALL update_user(:id, :username, :emailAdress, :password);", nativeQuery = true)
    void updateUser(@Param("id") UUID id, @Param("username") String username, @Param("emailAdress") String emailAdress, @Param("password") String password);
}

