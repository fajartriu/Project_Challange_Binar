package project.Challenge_6BinarFood.service.promo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import project.Challenge_6BinarFood.dto.data.Promo;
import project.Challenge_6BinarFood.model.user.User;
import project.Challenge_6BinarFood.repository.product.ProductRepository;

import java.util.List;

@Service
public class PromoService {
    private final JavaMailSender javaMailSender;
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    public PromoService(JavaMailSender javaMailSender, ProductRepository productRepository, EntityManager entityManager) {
        this.javaMailSender = javaMailSender;
        this.productRepository = productRepository;
        this.entityManager = entityManager;
    }

    public List<Promo> getRandomProducts(Integer limit) {
        TypedQuery<Promo> query = entityManager.createQuery(
                        "SELECT NEW project.Challenge_6BinarFood.dto.data.Promo(p.id, m.merchantName, p.productName, p.price, Round(3+ (random() * 4)) * 10, p.price - (p.price * (Round(3+ (random() * 4)) / 10))) FROM Product p JOIN p.merchant m ORDER BY function('RANDOM')", Promo.class)
                .setMaxResults(limit);

        return query.getResultList();
    }
    public void sendPromotionMail(List<Promo> promotionProductList, List<User> userList) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        for (User user : userList) {
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject("Promo Binar");
            System.out.println(user.getEmail());
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append("<html><body>");
            htmlContent.append("<h2>").append("Promo Binar Food").append("</h2>");
            for (Promo product: promotionProductList){
                htmlContent.append("<h3>").append("Beli ").append(product.getProductName()).append(" diskon ")
                        .append(product.getPercent()).append("%.").append(" Jadi harga Rp")
                        .append(product.getPriceDiscount()).append(" dari harga Rp")
                        .append(product.getPrice()).append(". Anda dapat beli beli produk tersebut di toko ")
                        .append(product.getMerchantName()).append("</h3>");
                System.out.println(product.getProductName());
                System.out.println(product.getPercent());
                System.out.println(product.getPrice());
                System.out.println(product.getPriceDiscount());
            }
            htmlContent.append("<h2>").append("Ayo buruan beli keburu habis").append("</h2>");
            mimeMessageHelper.setText(htmlContent.toString(), true);
            htmlContent.append("</body></html>");
            javaMailSender.send(mimeMessage);
        }
    }
}
