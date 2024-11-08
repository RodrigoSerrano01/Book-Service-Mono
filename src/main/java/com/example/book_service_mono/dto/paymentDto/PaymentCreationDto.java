package com.example.book_service_mono.dto.paymentDto;

import com.example.book_service_mono.domain.emailDomain.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for creating a new payment.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationDto {

    @NotBlank
    private String cartId;

    @NotBlank
    private Email userEmail;
}
