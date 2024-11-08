package com.example.book_service_mono.exception;

public class CartAlreadyExists extends RuntimeException {

    public CartAlreadyExists(String message) {
        super(message);
    }
}
