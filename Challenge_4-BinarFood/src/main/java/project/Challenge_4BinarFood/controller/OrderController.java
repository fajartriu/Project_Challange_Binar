package project.Challenge_4BinarFood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import project.Challenge_4BinarFood.model.merchant.CreateMerchantRequest;
import project.Challenge_4BinarFood.model.order.OrderRequest;
import project.Challenge_4BinarFood.response.MerchantResponse;
import project.Challenge_4BinarFood.response.OrderResponse;
import project.Challenge_4BinarFood.response.ProductResponse;
import project.Challenge_4BinarFood.response.WebResponse;
import project.Challenge_4BinarFood.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

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
            path = "/api/users/getAllOrderWithPagination/{page}/{pageSize}/{sortData}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<OrderResponse>> getAllOrderWithPaginationSort(@PathVariable int page, @PathVariable int pageSize, @PathVariable String sortData){
       try {
           List<OrderResponse> allUserOrder = orderService.getAllOrderWithPaginationSort(page,pageSize, sortData);
           return WebResponse.<List<OrderResponse>>builder().countRecord(allUserOrder.size()).data(allUserOrder).messages("Success").build();
       }catch (Exception e){
           return WebResponse.<List<OrderResponse>>builder().data(null).messages(e.getMessage()).build();
       }
    }
}
