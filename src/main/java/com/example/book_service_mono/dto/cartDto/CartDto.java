package com.example.book_service_mono.dto.cartDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {

    @NotBlank
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private List<String> booksIds;
}
