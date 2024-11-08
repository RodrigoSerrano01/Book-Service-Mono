package com.example.book_service_mono.domain.bookDomain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a Book entity in the MongoDB database.
 */
@Data
@NoArgsConstructor
@Document(collection = "books") // Specifies that this class is a MongoDB document and maps  to the "books" collection.
public class Book {

    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String genre;

    @NotNull
    private float price;

    @NotNull
    private int quantity;
}
