package com.example.book_service_mono.exception;

public class PaymentCreationException extends RuntimeException {

    public PaymentCreationException(String message) {
        super(message);
    }
}
