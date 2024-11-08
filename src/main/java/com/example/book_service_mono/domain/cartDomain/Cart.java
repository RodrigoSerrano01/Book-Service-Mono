package com.example.book_service_mono.domain.cartDomain;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {

    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private List<String> booksIds;
}
