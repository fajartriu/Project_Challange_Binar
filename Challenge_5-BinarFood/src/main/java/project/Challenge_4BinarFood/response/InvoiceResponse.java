package project.Challenge_4BinarFood.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceResponse {
    private LocalDate date_order;
    private LocalTime time_order;
    private String username;
    private String product_name;
    private String merchant_name;
    private String destination_address;
//    private String merchant_location;
    private boolean completed;
    private Double price;
    private Integer quantity;
    private Double sub_total;
    private Double total_price;
}
