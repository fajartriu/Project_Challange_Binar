package project.Challenge_6BinarFood.scheduler;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.Challenge_6BinarFood.dto.data.Promo;
import project.Challenge_6BinarFood.model.product.Product;
import project.Challenge_6BinarFood.model.user.User;
import project.Challenge_6BinarFood.repository.product.ProductRepository;
import project.Challenge_6BinarFood.repository.user.UserRepository;
import project.Challenge_6BinarFood.service.promo.PromoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
public class PromotionScheduler {
    private final UserRepository userRepository;
    private final PromoService promoService;

    @Scheduled(cron = "0 0 12 * * *") //setiap hari di pukul 12
    public void cronJob() throws MessagingException {
        List<User> users = userRepository.findAll();
        List<Promo> randomProducts = promoService.getRandomProducts(2);
        promoService.sendPromotionMail(randomProducts, users);
    }
}
