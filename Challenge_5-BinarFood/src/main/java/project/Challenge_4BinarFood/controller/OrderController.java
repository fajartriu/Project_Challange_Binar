package project.Challenge_4BinarFood.controller;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.Challenge_4BinarFood.model.merchant.CreateMerchantRequest;
import project.Challenge_4BinarFood.model.order.OrderRequest;
import project.Challenge_4BinarFood.response.*;
import project.Challenge_4BinarFood.service.InvoiceService;
import project.Challenge_4BinarFood.service.OrderService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    InvoiceService invoiceService;

    @PostMapping(
            path = "/api/users/{userId}/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<OrderResponse> orderProduct(@PathVariable UUID userId, @RequestBody OrderRequest request){
        try {
            OrderResponse orderResponse = orderService.orderProduct(userId, request);
            return WebResponse.<OrderResponse>builder().countRecord(1).data(orderResponse).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<OrderResponse>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/users/{userId}/getAllUserOrder",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrderResponse>> getAllUserOrder(@PathVariable UUID userId){
        try {
            List<OrderResponse> allUserOrder = orderService.getAllUserOrder(userId);
            return WebResponse.<List<OrderResponse>>builder().countRecord(allUserOrder.size()).data(allUserOrder).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<OrderResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/users/getAllOrder",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrderResponse>> getAllOrder(){
        try {
            List<OrderResponse> allUserOrder = orderService.getAllOrder();
            return WebResponse.<List<OrderResponse>>builder().countRecord(allUserOrder.size()).data(allUserOrder).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<OrderResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/users/getAllOrderWithPagination/{page}/{pageSize}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrderResponse>> getAllOrderWithPagination(@PathVariable int page, @PathVariable int pageSize){
        try {
            List<OrderResponse> allUserOrder = orderService.getAllOrderWithPagination(page,pageSize);
            return WebResponse.<List<OrderResponse>>builder().countRecord(allUserOrder.size()).data(allUserOrder).messages("Success").build();
        }catch (Exception e){
            return WebResponse.<List<OrderResponse>>builder().data(null).messages(e.getMessage()).build();
        }
    }

    @GetMapping(
            path = "/api/users/getAllOrderWithPagination/{page}/{pageSize}/{filter}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrderResponse>> getAllOrderWithPaginationSort(@PathVariable int page, @PathVariable int pageSize, @PathVariable String filter){
       try {
           List<OrderResponse> allUserOrder = orderService.getAllOrderWithPaginationSort(page,pageSize, filter);
           return WebResponse.<List<OrderResponse>>builder().countRecord(allUserOrder.size()).data(allUserOrder).messages("Success").build();
       }catch (Exception e){
           return WebResponse.<List<OrderResponse>>builder().data(null).messages(e.getMessage()).build();
       }
    }

//    @GetMapping(
//            path = "/api/reportMerchantTes/{merchantId}/{date}",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public WebResponse<List<ReportMerchantResponse>> generateReportMerchant(@PathVariable UUID merchantId, @PathVariable LocalDate date){
//        try {
//            List<ReportMerchantResponse> reportMerchantResponses = invoiceService.generateReportMerchant(merchantId, date);
//            return WebResponse.<List<ReportMerchantResponse>>builder().countRecord(reportMerchantResponses.size()).data(reportMerchantResponses).messages("Success").build();
//        }catch (Exception e){
//            return WebResponse.<List<ReportMerchantResponse>>builder().data(null).messages(e.getMessage()).build();
//        }
//    }

    @PostMapping(
            path = "/api/reportMerchantAllPayOrNot/{merchantId}/{format}"
    )
    public String exportReportMerchant(@PathVariable String format, @PathVariable UUID merchantId) throws JRException, FileNotFoundException {
        return invoiceService.exportReportMerchant( merchantId, format);
    }

    @PostMapping(
            path = "/api/reportMerchantWeek/{merchantId}/{date}/{format}"
    )
    public String exportReportMerchantWeek(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable LocalDate date) throws JRException, FileNotFoundException {
        return invoiceService.exportReportMerchantWeek( merchantId, format, date);
    }

    @PostMapping(
            path = "/api/reportMerchantMonth/{merchantId}/{month}/{format}"
    )
    public String exportReportMerchantMonth(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable String month) throws JRException, FileNotFoundException {
        return invoiceService.exportReportMerchantMonth( merchantId, format, month);
    }

    @PostMapping(
            path = "/api/reportMerchantCustom/{merchantId}/{startDate}/{endDate}/{format}"
    )
    public String exportReportMerchantCustom(@PathVariable String format, @PathVariable UUID merchantId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate) throws JRException, FileNotFoundException {
        return invoiceService.exportReportMerchantCustom( merchantId, format, startDate, endDate);
    }

    @PostMapping(
            path = "/api/reportUserInvoice/{userId}/{format}"
    )
    public String exportInvoiceUser(@PathVariable String format, @PathVariable UUID userId) throws JRException, FileNotFoundException {
        return invoiceService.exportInvoiceUser(userId, format);
    }

}
