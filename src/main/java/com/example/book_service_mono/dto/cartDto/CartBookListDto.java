package com.example.book_service_mono.dto.cartDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartBookListDto {

    @NotNull
    private List<String> booksIds;
}
