package project.Challenge_6BinarFood.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.Challenge_6BinarFood.dto.data.InvoiceOrder;
import project.Challenge_6BinarFood.dto.data.Promo;
import project.Challenge_6BinarFood.model.product.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("UPDATE Product p SET p.deleteProduct = true where p.id = :id")
    void deleteProduct(UUID id);

//    @Query("select new project.Challenge_6BinarFood.dto.data.Promo(p.id, m.merchantName, p.productName, p.price) FROM Product p join Merchant m on p.merchant = m.id ORDER BY RANDOM() LIMIT 2")
//    List<Promo> getProductPromo();

}
