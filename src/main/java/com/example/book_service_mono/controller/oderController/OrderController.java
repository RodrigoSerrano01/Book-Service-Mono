package com.example.book_service_mono.controller.oderController;

import com.example.book_service_mono.controller.RootController;

import com.example.book_service_mono.domain.orderDomain.Order;
import com.example.book_service_mono.dto.mapping.orderMapping.OrderMapper;
import com.example.book_service_mono.dto.orderDto.OrderCreationDto;
import com.example.book_service_mono.dto.orderDto.OrderDto;
import com.example.book_service_mono.service.impl.orderImpl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController extends RootController {

    private final OrderServiceImpl orderServiceImpl;
    private final OrderMapper orderMapper;

    @GetMapping(path = "/orders")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> fetchAllOrders() {
        return orderServiceImpl.findAllOrders();
    }

    @GetMapping(path = "/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto findOrderById(@PathVariable String orderId) {
        final Order order = orderServiceImpl.findOrderById(orderId);
        return orderMapper.orderToOrderDto(order);
    }

    @PostMapping(path = "/orders/order.add")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto addOrder(@Valid @RequestBody OrderCreationDto orderCreationDto) {
        return orderServiceImpl.addOrder(orderCreationDto);
    }

    @DeleteMapping(path = "/orders/{orderId}/order.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addOrder(@PathVariable String orderId) {
        orderServiceImpl.deleteOrderById(orderId);
    }
}
