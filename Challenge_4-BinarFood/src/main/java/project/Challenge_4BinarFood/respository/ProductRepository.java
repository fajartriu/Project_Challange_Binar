package project.Challenge_4BinarFood.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.Challenge_4BinarFood.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("UPDATE Product p SET p.deleteProduct = true where p.id = :id")
    void deleteProduct(UUID id);

}
