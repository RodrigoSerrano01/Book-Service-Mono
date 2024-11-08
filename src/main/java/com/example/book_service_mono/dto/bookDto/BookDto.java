package com.example.book_service_mono.dto.bookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) of book.
 */
@Data
public class BookDto {

    @NotBlank
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String genre;

    @NotNull
    private Float price;

    @NotNull
    private Integer quantity;
}