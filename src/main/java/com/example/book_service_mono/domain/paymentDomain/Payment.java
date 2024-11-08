package com.example.book_service_mono.domain.paymentDomain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    @NotBlank
    private String cartId;

    @NotNull
    private Float amount;
}
