package com.example.book_service_mono.dto.bookDto;

import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for updating a new book.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateDto {

    private String title;

    private String author;

    private String genre;

    @Digits(integer = 10, fraction = 2)
    private Float price;

    private Integer quantity;
}
