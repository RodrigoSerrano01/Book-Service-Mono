package com.example.book_service_mono.exception.handler;

import com.example.book_service_mono.exception.BookAlreadyRegisteredException;
import com.example.book_service_mono.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.Instant;

/**
 * Global exception handler for handling specific exceptions and returning structured error responses.
 */
@ControllerAdvice // Annotation that allows handling exceptions across the whole application in one global handling component.
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            BookAlreadyRegisteredException.class
    })
    protected ResponseEntity<ErrorResponse> handleBadRequestException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST
                , exception.getMessage()
                , Instant.now())
                , HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({
            BookNotFoundException.class
    })
    protected ResponseEntity<ErrorResponse> handleNotFoundException(Exception exception) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND
                , exception.getMessage()
                , Instant.now())
                , HttpStatus.NOT_FOUND);
    }
}
