package project.Challenge_4BinarFood.service;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.Challenge_4BinarFood.entity.*;
import project.Challenge_4BinarFood.model.order.OrderDTO;
import project.Challenge_4BinarFood.model.order.OrderRequest;
import project.Challenge_4BinarFood.response.OrderResponse;
import project.Challenge_4BinarFood.respository.*;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public interface OrderService {
    OrderResponse orderProduct(UUID userId, OrderRequest request);
    List<OrderResponse> getAllUserOrder(UUID userId);
    List<OrderResponse> getAllOrder();
    List<OrderResponse> getAllOrderWithPagination(int page, int pageSize);
    List<OrderResponse> getAllOrderWithPaginationSort(int page, int pageSize, String sortData);
}
