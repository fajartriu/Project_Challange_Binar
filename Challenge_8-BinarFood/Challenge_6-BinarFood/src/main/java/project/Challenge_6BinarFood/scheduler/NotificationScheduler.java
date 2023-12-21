package project.Challenge_6BinarFood.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import project.Challenge_6BinarFood.dto.data.Promo;
import project.Challenge_6BinarFood.dto.request.fcm.NotificationRequest;
import project.Challenge_6BinarFood.service.fcm.FCMService;
import project.Challenge_6BinarFood.service.promo.PromoService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {
    private final FCMService fcmService;
    private final PromoService promoService;

    @Scheduled(cron = "0 0 12 * * *") //setiap hari di pukul 12
    public void notificationJob() throws ExecutionException, InterruptedException {
        List<Promo> randomProducts = promoService.getRandomProducts(1);
        StringBuilder stringBody = new StringBuilder();
        for (Promo promoProducts: randomProducts){
            stringBody.append("Beli ").append(promoProducts.getProductName()).append(" diskon ")
                    .append(promoProducts.getPercent()).append("%.").append(" Jadi harga Rp")
                    .append(promoProducts.getPriceDiscount()).append(" dari harga Rp")
                    .append(promoProducts.getPrice()).append(". Anda dapat beli beli produk tersebut di toko ")
                    .append(promoProducts.getMerchantName()).append(".").append("\n");
        }
        stringBody.append("Ayo segera beli keburu kehabisan !!!");

        NotificationRequest request = new NotificationRequest();
        request.setTitle("Promo Binar Food");
        request.setBody(stringBody.toString());
        request.setToken("eOkM6H0d-qLdz9JCojk4I9:APA91bFdmhY49zrfV3_s5zj74gjJXcQnvN2HsLVeudk8pncYDT7VbipvsQvkLeaohwcegdOO4xcLZy50-iEGMZ9b1khbYyCaIiwk8tXDBxlUk2w_Aiz3eLEysOXukVjqGjXuN9x1qpJl");
//        System.out.println(notificationRequest.getBody());
        fcmService.sendMessageToToken(request);
    }
}
