package project.Challenge_4BinarFood.service;

import org.springframework.stereotype.Service;
import project.Challenge_4BinarFood.model.order.OrderRequest;
import project.Challenge_4BinarFood.response.OrderResponse;

import java.util.*;

@Service
public interface OrderService {
    OrderResponse orderProduct(UUID userId, OrderRequest request);
    List<OrderResponse> getAllUserOrder(UUID userId);
    List<OrderResponse> getAllOrder();
    List<OrderResponse> getAllOrderWithPagination(int page, int pageSize);
    List<OrderResponse> getAllOrderWithPaginationSort(int page, int pageSize, String filter);
}
