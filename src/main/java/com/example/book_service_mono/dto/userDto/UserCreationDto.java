package com.example.book_service_mono.dto.userDto;

import com.example.book_service_mono.domain.emailDomain.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for creating a new user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationDto {

    @NotBlank
    @Size(min = 3, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 20)
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    private Email email;

    @NotBlank
    @Pattern(regexp = "^(?!0{3})(?!6{3})[0-8]\\d{2}-(?!0{2})\\d{2}-(?!0{4})\\d{4}$")
    private String ssn;
}
