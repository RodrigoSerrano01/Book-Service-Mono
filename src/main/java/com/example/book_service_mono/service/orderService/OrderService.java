package com.example.book_service_mono.service.orderService;


import com.example.book_service_mono.domain.orderDomain.Order;
import com.example.book_service_mono.dto.orderDto.OrderCreationDto;
import com.example.book_service_mono.dto.orderDto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    List<OrderDto> findAllOrders();

    Order findOrderById(String id);

    OrderDto addOrder(OrderCreationDto orderCreationDto);

    void deleteOrderById(String orderId);
}
