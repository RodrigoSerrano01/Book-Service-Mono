package com.example.book_service_mono.controller.paymantController;


import com.example.book_service_mono.controller.RootController;
import com.example.book_service_mono.domain.paymentDomain.Payment;
import com.example.book_service_mono.dto.mapping.paymentMapping.PaymentMapper;
import com.example.book_service_mono.dto.paymentDto.PaymentCreationDto;
import com.example.book_service_mono.dto.paymentDto.PaymentDto;
import com.example.book_service_mono.service.impl.paymentImpl.PaymentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class where Payment data will be created,
 * read, updated and deleted (CRUD) through Payment-Service APIs.
 */
@RestController
@RequiredArgsConstructor
public class PaymentController extends RootController {

    private final PaymentServiceImpl paymentServiceImpl;
    private final PaymentMapper paymentMapper;

    /**
     * Endpoint to fetch all payments.
     *
     * @return List of all payments.
     */
    @GetMapping(path = "/payments")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> fetchAllPayments() {
        return paymentServiceImpl.fetchAllPayments();
    }

    /**
     * Endpoint to fetch a payment by its id.
     *
     * @param paymentId The id of the payment to fetch.
     * @return The payment with the specified id.
     */
    @GetMapping(path = "/payments/{paymentId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto fetchPaymentById(@Valid @PathVariable String paymentId) {
        final Payment payment = paymentServiceImpl.findPaymentById(paymentId);
        return paymentMapper.paymentToPaymentDto(payment);
    }

    /**
     * Endpoint to fetch a payment by its cart id.
     *
     * @param cartId cartId.
     * @return the payment with the specified cart id.
     */
    @GetMapping(path = "/payments/cart/{cartId}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto fetchPaymentByCartId(@Valid @PathVariable String cartId) {
        return paymentServiceImpl.findPaymentByCartId(cartId);
    }

    /**
     * Endpoint to add a new Payment.
     *
     * @param paymentCreationDto DTO containing the fields of the payment to be added.
     * @return The added payment.
     */
    @PostMapping(path = "/payments/payment.add")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto addPayment(@Valid @RequestBody PaymentCreationDto paymentCreationDto) {
        return paymentServiceImpl.addPayment(paymentCreationDto);
    }

    /**
     * Endpoint to delete a payment.
     *
     * @param paymentId The ID of the payment to delete.
     */
    @DeleteMapping(path = "/payments/{paymentId}/payment.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePayment(@PathVariable String paymentId) {
        paymentServiceImpl.deletePayment(paymentId);
    }
}
