package Challenge_7BinarFood.Invoice.client;

import Challenge_7BinarFood.Invoice.DTO.InvoiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="Order-InvoiceByUser-Service", url = "http://localhost:8080/api/v1/invoice")
public interface OrderClient {
//    @GetMapping("/userOrderByUser")
//    List<InvoiceOrder> invoiceUser(Principal principal);

    @GetMapping("/userOrderByUser")
    List<InvoiceResponse> invoiceUser(String username);
}
