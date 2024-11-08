package com.example.book_service_mono.service.paymentService;


import com.example.book_service_mono.domain.paymentDomain.Payment;
import com.example.book_service_mono.dto.paymentDto.PaymentCreationDto;
import com.example.book_service_mono.dto.paymentDto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<PaymentDto> fetchAllPayments();

    Payment findPaymentById(String id);

    PaymentDto addPayment(PaymentCreationDto paymentCreationDto);

    void deletePayment(String id);

    PaymentDto findPaymentByCartId(String cartId);
}
