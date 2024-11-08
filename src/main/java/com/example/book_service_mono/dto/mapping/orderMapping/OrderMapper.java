package com.example.book_service_mono.dto.mapping.orderMapping;


import com.example.book_service_mono.domain.orderDomain.Order;
import com.example.book_service_mono.dto.orderDto.OrderCreationDto;
import com.example.book_service_mono.dto.orderDto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderMapper {

    Order orderCreationToOrder(OrderCreationDto orderCreationDto);

    OrderDto orderToOrderDto(Order order);

    List<OrderDto> orderListToOrderListDto(List<Order> orders);
}
