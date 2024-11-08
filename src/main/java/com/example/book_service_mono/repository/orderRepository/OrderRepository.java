package com.example.book_service_mono.repository.orderRepository;


import com.example.book_service_mono.domain.orderDomain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

}
