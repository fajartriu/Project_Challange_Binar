package project.Challenge_4BinarFood.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import project.Challenge_4BinarFood.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
   private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

   private final MerchantRepository merchantRepository;
   private final ProductRepository productRepository;
   private final UsersRepository usersRepository;
   private final OrderRepository orderRepository;
   private final OrderDetailRepository orderDetailRepository;
   private final ValidationService validationService;

   @Autowired
   public OrderServiceImpl(MerchantRepository merchantRepository, ProductRepository productRepository, UsersRepository usersRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, ValidationService validationService) {
       this.merchantRepository = merchantRepository;
       this.productRepository = productRepository;
       this.usersRepository = usersRepository;
       this.orderRepository = orderRepository;
       this.orderDetailRepository = orderDetailRepository;
       this.validationService = validationService;
   }

    @Transactional
    @Override
    public OrderResponse orderProduct(UUID userId, OrderRequest request) {
        try {
            validationService.validate(request);

            Users users = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found"));
            Order order = new Order();
            order.setId(UUID.randomUUID());
            order.setOrder_time(LocalDateTime.now());
            order.setDestination_address(request.getDestinationAddress());
            order.setUser(users);
            order.setCompleted(false);
            orderRepository.save(order);
            logger.info("Success store data order");

            Product product = productRepository.findById(request.getProductID())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product is not found"));

            Merchant merchant = merchantRepository.findById(product.getMerchant().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant is not found"));

            Double priceProduct = product.getPrice();
            logger.debug(priceProduct.toString());
            Integer quantityOrder = request.getQuantity();
            logger.debug(quantityOrder.toString());
            Double total = priceProduct * quantityOrder;
            logger.debug(total.toString());


            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(UUID.randomUUID());
            orderDetail.setProduct(product);
            orderDetail.setOrder(order);
            orderDetail.setQuantity(request.getQuantity());
            orderDetail.setTotal_price(total);
            orderDetailRepository.save(orderDetail);
            logger.info("Success store data order detail");

            return OrderResponse.builder()
                    .orderTime(order.getOrder_time())
                    .productName(product.getProductName())
                    .merchantName(merchant.getMerchantName())
                    .completed(order.isCompleted())
                    .destinationAddress(order.getDestination_address())
                    .username(users.getUsername())
                    .price(product.getPrice())
                    .quantity(orderDetail.getQuantity())
                    .totalPrice(total)
                    .build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<OrderResponse> getAllUserOrder(UUID userId) {
        List<OrderDTO> allUserOrderDetail = orderDetailRepository.getAllUserOrder(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        if (!allUserOrderDetail.isEmpty()){
            for (OrderDTO orderDTO: allUserOrderDetail){
                orderResponses.add(new OrderResponse(orderDTO.getOrderTime(), orderDTO.getUsername(), orderDTO.getProductName(), orderDTO.getMerchantName(), orderDTO.getDestinationAddress(), orderDTO.getQuantity(), orderDTO.getPrice(), orderDTO.getTotalPrice(), orderDTO.isCompleted()));
                logger.debug(orderResponses.toString());
            }
        }

        if (!orderResponses.isEmpty()){
            logger.info("Data response success " + orderResponses);
            return orderResponses;
        }
        logger.error("Response Data is empty: " + Collections.emptyList());
        return Collections.emptyList();
    }

    @Override
    public List<OrderResponse> getAllOrder() {
        List<OrderDTO> allOrderDetail = orderDetailRepository.getAllOrder();
        List<OrderResponse> orderResponses = new ArrayList<>();
        if (!allOrderDetail.isEmpty()){
            for (OrderDTO orderDTO: allOrderDetail){
                orderResponses.add(new OrderResponse(orderDTO.getOrderTime(),orderDTO.getUsername(), orderDTO.getProductName(), orderDTO.getMerchantName(), orderDTO.getDestinationAddress(), orderDTO.getQuantity(), orderDTO.getPrice(), orderDTO.getTotalPrice(), orderDTO.isCompleted()));
                logger.debug(orderResponses.toString());
            }
        }

        if (!orderResponses.isEmpty()){
            logger.info("Data response success " + orderResponses);
            return orderResponses;
        }
        logger.error("Response Data is empty: " + Collections.emptyList());
        return Collections.emptyList();
    }

    @Override
    public List<OrderResponse> getAllOrderWithPagination(int page, int pageSize) {
        List<OrderDTO> allOrderDetailPagination = orderDetailRepository.paginationProduct(PageRequest.of(page, pageSize));
        List<OrderResponse> orderResponses = new ArrayList<>();

        if (!allOrderDetailPagination.isEmpty()){
            for (OrderDTO orderDTO: allOrderDetailPagination){
                orderResponses.add(new OrderResponse(orderDTO.getOrderTime(),orderDTO.getUsername(), orderDTO.getProductName(), orderDTO.getMerchantName(), orderDTO.getDestinationAddress(), orderDTO.getQuantity(), orderDTO.getPrice(), orderDTO.getTotalPrice(), orderDTO.isCompleted()));
                logger.debug(orderResponses.toString());
            }
        }

        if (!orderResponses.isEmpty()){
            logger.info("Data response success " + orderResponses);
            return orderResponses;
        }
        logger.error("Response Data is empty: " + Collections.emptyList());
        return Collections.emptyList();
    }

    @Override
    public List<OrderResponse> getAllOrderWithPaginationSort(int page, int pageSize, String sortData) {
        List<OrderDTO> allOrderDetailPaginationSort = orderDetailRepository.paginationProduct(PageRequest.of(page, pageSize, Sort.by(sortData)));
        List<OrderResponse> orderResponses = new ArrayList<>();
        if (!allOrderDetailPaginationSort.isEmpty()){
            for (OrderDTO orderDTO: allOrderDetailPaginationSort){
                orderResponses.add(new OrderResponse(orderDTO.getOrderTime(),orderDTO.getUsername(), orderDTO.getProductName(), orderDTO.getMerchantName(), orderDTO.getDestinationAddress(), orderDTO.getQuantity(), orderDTO.getPrice(), orderDTO.getTotalPrice(), orderDTO.isCompleted()));
                logger.debug(orderResponses.toString());
            }
        }

        if (!orderResponses.isEmpty()){
            logger.info("Data response success " + orderResponses);
            return orderResponses;
        }
        logger.error("Response Data is empty: " + Collections.emptyList());
        return Collections.emptyList();
    }
}
