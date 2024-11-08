package com.example.book_service_mono.domain.userDomain;

import com.example.book_service_mono.domain.emailDomain.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents an User entity in the MongoDB database.
 */
@Data
@NoArgsConstructor
@Document(collection = "users") // Specifies that this class is a MongoDB document and maps it to the "users" collection.
public class User {

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

    @NotNull
    private List<String> booksIds;
}
