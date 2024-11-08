package com.example.book_service_mono.repository.paymentRepository;


import com.example.book_service_mono.domain.paymentDomain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findTopByCartId(String cartId);
}
