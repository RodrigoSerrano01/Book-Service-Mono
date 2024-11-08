package com.example.book_service_mono.repository.cartRepository;


import com.example.book_service_mono.domain.cartDomain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    Optional<Cart> findCartById(String id);

    Optional<Cart> findTopByUserId(String userId);
}
