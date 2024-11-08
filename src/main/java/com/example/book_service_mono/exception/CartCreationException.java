package com.example.book_service_mono.exception;

public class CartCreationException extends RuntimeException {

    public CartCreationException(String message) {
        super(message);
    }
}
