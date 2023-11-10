package project.Challenge_4BinarFood.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.Challenge_4BinarFood.entity.OrderDetail;
import project.Challenge_4BinarFood.model.invoice.InvoiceOrderRequest;
import project.Challenge_4BinarFood.model.order.OrderDTO;
import project.Challenge_4BinarFood.response.ReportMerchantResponse;

import java.util.*;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID>  {
//    @Procedure("getAllUserOrder")
//    List<OrderDTO> getAllUserOrder(@Param("userId") UUID userId);

    @Query(value = "select * from getalluserorder a (:userId);", nativeQuery = true)
    List<OrderDTO> getOrderDetail(@Param("userId") UUID userId);

    @Query("select new project.Challenge_4BinarFood.model.order.OrderDTO(o.order_time, u.username, p.productName, " +
            "m.merchantName, o.destination_address, od.quantity, p.price, od.total_price, o.completed) from Users u " +
            "JOIN u.order o JOIN o.orderDetail od JOIN od.product p JOIN p.merchant m where u.id=:userId")
    List<OrderDTO> getAllUserOrder(@Param("userId") UUID userId);

    @Query("select new project.Challenge_4BinarFood.model.order.OrderDTO(o.order_time, u.username, p.productName, " +
            "m.merchantName, o.destination_address, od.quantity, p.price, od.total_price, o.completed) from Users u " +
            "JOIN u.order o JOIN o.orderDetail od JOIN od.product p JOIN p.merchant m")
    List<OrderDTO> getAllOrder();

    @Query("select new project.Challenge_4BinarFood.model.order.OrderDTO(o.order_time, u.username, p.productName, " +
            "m.merchantName, o.destination_address, od.quantity, p.price, od.total_price, o.completed) from Users u " +
            "JOIN u.order o JOIN o.orderDetail od JOIN od.product p JOIN p.merchant m")
    List<OrderDTO> paginationProduct(PageRequest pageRequest);

//    @Query("select new project.Challenge_4BinarFood.model.order.OrderDTO(o.order_time, p.productName, m.merchantName, o.destination_address, od.quantity, p.price, od.total_price, o.completed) from Users u JOIN u.order o JOIN o.orderDetail od JOIN od.product p JOIN p.merchant m order by o.order_time desc")
//    List<OrderDTO> getAllOrderWithPagination(@Param("ofset") int ofset, @Param("pageset") int pageset);

//    @Query(" ")
//    List<OrderDTO> getAllOrderWithPagination(@Param("size") int size, @Param("pageSize") int pageSize);

    @Query(value = "select * from page(:page, :pageSize);", nativeQuery = true)
    List<Map<String, OrderDTO>> getAllOrderWithPagination(@Param("page") int page, @Param("pageSize") int pageSize);

//    new project.Challenge_4BinarFood.model.order.OrderDTO(a.order_time, a.username, a.productName, a.merchantName, a.destination_address, a.quantity, a.price, a.total_price, a.completed)

    @Query("select new project.Challenge_4BinarFood.model.invoice.InvoiceOrderRequest(o.id, o.order_time, u.username, p.productName, " +
            "m.merchantName, o.destination_address, od.quantity, p.price, od.total_price, o.completed) from Users u " +
            "JOIN u.order o JOIN o.orderDetail od JOIN od.product p JOIN p.merchant m where u.id=:userId")
    List<InvoiceOrderRequest> getAllUserOrderDetailInvoice(@Param("userId") UUID userId);

}
