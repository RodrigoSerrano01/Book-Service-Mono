package com.example.book_service_mono.domain.emailDomain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Email {

    private String to;
    private String subject;
    private String body;
}
