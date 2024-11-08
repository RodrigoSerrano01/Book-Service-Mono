package com.example.book_service_mono.exception;

public class BookQuantityException extends RuntimeException {

    public BookQuantityException(String message) {
        super(message);
    }
}
