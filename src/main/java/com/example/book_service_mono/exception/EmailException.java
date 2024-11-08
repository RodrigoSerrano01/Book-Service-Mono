package com.example.book_service_mono.exception;

public class EmailException extends RuntimeException {

    public EmailException(String message) {
        super(message);
    }
}
