package com.example.book_service_mono.exception;

/**
 * Exception to be thrown when book is already registered in the database.
 */
public class BookAlreadyRegisteredException extends RuntimeException {

    public BookAlreadyRegisteredException(String message) {
        super(message);
    }

}
