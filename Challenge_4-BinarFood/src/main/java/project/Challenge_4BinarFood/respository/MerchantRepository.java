package project.Challenge_4BinarFood.respository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.Challenge_4BinarFood.entity.Merchant;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    @Query("SELECT m FROM Merchant m WHERE m.open = :open")
    List<Merchant> findMerchantByOpen(boolean open);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "SELECT * FROM getMerchantId(:productName,:merchantName); ;", nativeQuery = true)
    List<Merchant> getMerchantByPnameMname(@Param("productName") String productName, @Param("merchantName") String merchantName);
}
