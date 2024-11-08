package com.example.book_service_mono.enumerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum representing different error types and their messages.
 */
@Getter
@AllArgsConstructor
public enum Error {

    NO_BOOKS_FOUND("NO_BOOKS_FOUND", "No books were found."),
    NO_BOOK_FOUND_BY_ID("NO_BOOK_FOUND_BY_ID", "No book was found with the given id."),
    NO_BOOK_FOUND_BY_TITLE("NO_BOOK_FOUND_BY_TITLE", "No book was found with the given title."),
    BOOK_ALREADY_REGISTERED("BOOK_ALREADY_REGISTERED", "Book is already registered."),
    INVALID_BOOK_QUANTITY_MESSAGE("INVALID_BOOK_QUANTITY_MESSAGE",
            "The book could not be purchased because there are no more books with id "),
    NO_USERS_FOUND("NO_USERS_FOUND", "No users were found."),
    NO_USER_FOUND_BY_ID("NO_USER_FOUND_BY_ID", "No user was found by ID."),
    NO_USER_FOUND_BY_FIRST_AND_LAST_NAME("NO_USER_FOUND_BY_FIRST_AND_LAST_NAME", "No user was found by first and last name."),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "User already exists."),
    NO_CART_FOUND_BY_ID("NO_CART_FOUND_BY_ID", "No cart was found by ID."),
    CART_ALREADY_EXISTS("CART_ALREADY_EXISTS", "Cart already exists for the given user."),
    NO_PAYMENT_FOUND("NO_PAYMENT_FOUND", "No payment was found."),
    NO_PAYMENT_FOUND_BY_ID("NO_PAYMENT_FOUND_BY_ID", "No payment was found with the given id."),PAYMENT_NOT_FOUND("PAYMENT_NOT_FOUND", "Pagamento não encontrado"),
    ORDER_NOT_FOUND("ORDER_NOT_FOUND", "Pedido não encontrado"),
    CART_NOT_FOUND("CART_NOT_FOUND", "Carrinho não encontrado"),
    BOOK_NOT_FOUND("BOOK_NOT_FOUND", "Livro não encontrado"),
    USER_NOT_FOUND("USER_NOT_FOUND", "Usuário não encontrado");
;
    private final String errorMessage;
    private final String errorDescription;


}
