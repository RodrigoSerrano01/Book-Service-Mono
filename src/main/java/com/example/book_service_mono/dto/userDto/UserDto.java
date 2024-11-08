package com.example.book_service_mono.dto.userDto;

import com.example.book_service_mono.domain.emailDomain.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Data Transfer Object (DTO) of user.
 */
@Data
public class UserDto {

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private Email email;

    @NotBlank
    private String ssn;

    private List<String> booksIds;
}
